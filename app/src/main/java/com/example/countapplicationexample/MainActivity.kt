package com.example.countapplicationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.countapplicationexample.ui.theme.CountApplicationExampleTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.material3.Button as Button


class MainViewModel: ViewModel() {

    val currentValue = 0
    private val _counter: MutableStateFlow<Int> = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun onButtonClick1() {
        _counter.update { currentValue -> currentValue + 1 }
    }
    fun onButtonClick2() {
        _counter.update { currentValue -> currentValue - 1}
    }

}

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val count by viewModel.counter.collectAsStateWithLifecycle()
                Column (verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                    )   {
                    Text(count.toString())
                    Button(onClick = viewModel::onButtonClick1) {
                        Text("Count")
                    }
                    Button(onClick = viewModel::onButtonClick2) {
                        Text("Decrease")
                    }
                }
            }
        }
    }
}
