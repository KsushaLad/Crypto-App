package com.ksusha.crypto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksusha.crypto.repository.ApiRepository
import com.ksusha.crypto.response.ResponseDetailsCoin
import com.ksusha.crypto.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

        private val _detailsCoin = MutableLiveData<DataStatus<ResponseDetailsCoin>>()
        val detailsCoin: LiveData<DataStatus<ResponseDetailsCoin>>
            get() = _detailsCoin

        fun getDetailsCoin(id: String) = viewModelScope.launch {
            repository.getDetailsCoin(id).collect{
                _detailsCoin.value=it
            }
        }

}