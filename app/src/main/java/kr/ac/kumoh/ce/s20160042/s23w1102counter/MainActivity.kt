package kr.ac.kumoh.ce.s20160042.s23w1102counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20160042.s23w1102counter.ui.theme.S23W1102CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm1 = ViewModelProvider(this)[CounterViewModel::class.java]
        val vm2 = ViewModelProvider(this)[CounterViewModel::class.java]
        // viewModel은 싱글 객체라서 변수를 2개 만들어도 하나같이 동작함
        setContent {
            //MyApp(content = { Greeting("test") })
            MyApp {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    //Clicker()
                    Counter(vm1)
                    //Counter(vm2)
                }

            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    S23W1102CounterTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Composable
fun Clicker() {
    //var txtString by remember { mutableStateOf("눌러주세요") }
    val (txtString, setTxtString) = remember {
        mutableStateOf("눌러주세요") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = txtString,
            fontSize = 70.sp,
        )
        Button(modifier = Modifier
            .fillMaxWidth(),
            onClick = {
                setTxtString("눌렸습니다")
            }) {
            // Text(text = "눌러봐")
            Text("눌러봐")
        }
    }
}

@Composable
fun Counter(viewModel: CounterViewModel) {
    //var count = 0;
    val count by viewModel.count.observeAsState(0)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$count",
            fontSize = 70.sp,
        )
        Row {
            Button(modifier = Modifier.weight(1f),
                onClick = { viewModel.onAdd() }) {
                Text(text = "증가")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(modifier = Modifier.weight(1f),
                onClick = { viewModel.onSub() }) {
                Text(text = "감소")
            }
        }
    }

}