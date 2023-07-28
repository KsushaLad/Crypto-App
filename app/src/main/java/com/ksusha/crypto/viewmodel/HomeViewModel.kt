package com.ksusha.crypto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksusha.crypto.repository.ApiRepository
import com.ksusha.crypto.response.ResponseCoinsMarkets
import com.ksusha.crypto.response.ResponseDetailsCoin
import com.ksusha.crypto.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ApiRepository
    ): ViewModel() {

    private val _coinsList =
        MutableLiveData<DataStatus<List<ResponseCoinsMarkets.ResponseCoinsMarketsItem>>>()
    val coinsList: LiveData<DataStatus<List<ResponseCoinsMarkets.ResponseCoinsMarketsItem>>>
        get() = _coinsList

    fun getCoinList(vsCurrency: String) = viewModelScope.launch {
        repository.getCoinsList(vsCurrency).collect {
            _coinsList.value = it
        }
    }

}