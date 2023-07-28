package com.ksusha.crypto.ui.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.ksusha.crypto.R
import com.ksusha.crypto.databinding.FragmentDetailBinding
import com.ksusha.crypto.utils.Constants.animationDuration
import com.ksusha.crypto.utils.DataStatus
import com.ksusha.crypto.utils.isVisible
import com.ksusha.crypto.utils.roundToThreeDecimals
import com.ksusha.crypto.utils.roundToTwoDecimals
import com.ksusha.crypto.utils.toDoubleToFloat
import com.ksusha.crypto.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        detailViewModel.getDetailsCoin(args.id)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openInfo()
    }

    private fun openInfo() {
        lifecycleScope.launch {
            binding.apply {
                detailViewModel.detailsCoin.observe(viewLifecycleOwner) {
                    when(it.status) {
                        DataStatus.Status.LOADING -> {
                            pBarLoading.isVisible(true, mainLayout)
                        }
                        DataStatus.Status.SUCCESS -> {
                            it.data?.let { data ->
                                pBarLoading.isVisible(false, mainLayout)
                                tvCoinNameSymbol.text = "${data.name} [ ${data.symbol?.uppercase()} ]"
                                tvCurrentPrice.text = data.marketData?.currentPrice?.eur?.roundToThreeDecimals()

                                val number = data.marketData?.priceChangePercentage24h?.roundToTwoDecimals()?.toDouble()!!
                                tvChangePercentage.text = "$number%"

                                when {
                                    number > 0 -> {
                                        tvChangePercentage.setTextColor(Color.GREEN)
                                        imgArrow.setImageResource(R.drawable.drop_up)
                                    }
                                    number < 0 -> {
                                        tvChangePercentage.setTextColor(Color.RED)
                                        imgArrow.setImageResource(R.drawable.drop_down)
                                    }
                                    else -> {
                                        tvChangePercentage.setTextColor(Color.LTGRAY)
                                        imgArrow.setImageResource(R.drawable.minimize)
                                    }
                                }
                                //Logo
                                imgCoinLogo.load(data.image?.large) {
                                    crossfade(true)
                                    crossfade(500)
                                    placeholder(R.drawable.bitcoin)
                                    error(R.drawable.bitcoin)
                                }
                                //Chart
                                lineChart.gradientFillColors = intArrayOf(
                                    Color.parseColor("#2a9085"), Color.TRANSPARENT
                                )
                                lineChart.animation.duration = animationDuration
                                val listData = data.marketData.sparkline7d?.price?.toDoubleToFloat()
                                lineChart.animate(listData!!)
                                //Categories
                                tvCategories.text = data.categories?.get(0)!!
                                //GenesisDate
                                tvGenesisDate.text = if (data.genesisDate != null && data.genesisDate.isNotEmpty()) data.genesisDate else "-"
                                //Link
                                tvLink.text = data.links?.homepage?.get(0)
                                tvLink.setOnClickListener {
                                    val uri = Uri.parse(data.links?.homepage?.get(0))
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    requireContext().startActivity(intent)
                                }
                                //Description
                                tvDescription.text = if (data.description?.en != null && data.description.en.isNotEmpty()) Jsoup.parse(data.description.en).text() else "-"
                                tvDescription.movementMethod = ScrollingMovementMethod()

                            }
                        }
                        DataStatus.Status.ERROR -> {
                            pBarLoading.isVisible(true, mainLayout)
                            Toast.makeText(requireContext(), "There is something wrong!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}