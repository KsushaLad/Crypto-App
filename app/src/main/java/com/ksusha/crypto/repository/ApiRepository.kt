package com.ksusha.crypto.repository

import com.ksusha.crypto.api.ApiService
import com.ksusha.crypto.utils.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCoinsList(vsCurrency: String) = flow {
        emit(DataStatus.loading())
        val result = apiService.getCoinsList(vsCurrency)
        when(result.code()) {
            200 -> { emit(DataStatus.success(result.body())) }
            400 -> { emit(DataStatus.error(result.message())) }
            500 -> { emit(DataStatus.error(result.message())) }
        }
    }.catch {
        emit(DataStatus.error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}