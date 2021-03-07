package com.abdullah996.anghamk.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdullah996.anghamk.exoplayer.MusicService
import com.abdullah996.anghamk.exoplayer.MusicServiceConnection
import com.abdullah996.anghamk.exoplayer.currentPlaybackPosition
import com.abdullah996.anghamk.utill.constants.UPDATE_PLAYER_POSITION_INTERVAL
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SongViewModel @ViewModelInject constructor(
        musicServiceConnection: MusicServiceConnection
) :ViewModel() {

    private val playbackState =musicServiceConnection.playbackState

    private val _curSongDuration=MutableLiveData<Long>()
    val curSongDuration:LiveData<Long> =_curSongDuration

    private val _curPlayerPosition=MutableLiveData<Long>()
    val curPlayerPosition:LiveData<Long> =_curPlayerPosition

    init {
        ubdateCurrentPlayerPosition()
    }

    private fun ubdateCurrentPlayerPosition(){
        viewModelScope.launch {
            while (true){
                val pos=playbackState.value?.currentPlaybackPosition
                if (curPlayerPosition.value!=pos){
                    _curPlayerPosition.postValue(pos)
                    _curSongDuration.postValue(MusicService.curSongDuration)
                }
                delay(UPDATE_PLAYER_POSITION_INTERVAL)
            }
        }
    }

}