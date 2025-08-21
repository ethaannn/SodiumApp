package com.ethan.sodium.app.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ethan.sodium.app.data.model.User
import com.ethan.sodium.app.ui.viewmodel.MultiStateViewModel
import com.ethan.sodium.ui.components.button.SodiumCustomButton
import com.ethan.sodium.ui.components.multistate.MultiState
import com.ethan.sodium.ui.components.multistate.MultiStateView
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MultiStateScreen() {
    val mViewModel: MultiStateViewModel = koinViewModel<MultiStateViewModel>()

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

        MultiStateView<User>(
            modifier = Modifier
                .fillMaxWidth().height(200.dp),
            viewModel = mViewModel,
            content = { it: User? ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Blue)
                ) {
                    Text(text = "测试")
                }

            })
        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SodiumCustomButton(text = "EmptyVew") {
                mViewModel.setMultiState(MultiState.EMPTY)
            }
            SodiumCustomButton(text = "LoadingView") {
                mViewModel.setMultiState(MultiState.LOADING)
            }
            SodiumCustomButton(text = "LoadingView") {
                mViewModel.setMultiState(MultiState.ERROR)
            }


        }
    }

}