package com.shoebill.maru.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shoebill.maru.model.data.landmark.Owner
import com.shoebill.maru.model.repository.LandmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LandmarkOwnerViewModel @Inject constructor(
    private val landmarkRepository: LandmarkRepository
) : ViewModel() {
    private val _owner = MutableLiveData(Owner())
    val owner get() = _owner

    private val _landmarkName = MutableLiveData("")
    val landmarkName get() = _landmarkName

    fun initLandmarkOwnerViewModel(landmarkId: Long, landmarkName: String? = null) {
        if (landmarkName == null) loadLandmarkName(landmarkId)
        else _landmarkName.value = landmarkName!!
        loadOwnerInfo(landmarkId)
    }

    private fun loadOwnerInfo(landmarkId: Long) {
        viewModelScope.launch {
            _owner.value = withContext(Dispatchers.IO) {
                landmarkRepository.getLandmarkOwner(landmarkId)
            }
        }
    }

    private fun loadLandmarkName(landmarkId: Long) {
        viewModelScope.launch {
            _landmarkName.value = withContext(Dispatchers.IO) {
                landmarkRepository.getLandmarkName(landmarkId)
            }
        }
    }
}