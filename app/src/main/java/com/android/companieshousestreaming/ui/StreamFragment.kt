package com.android.companieshousestreaming.ui

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.companieshousestreaming.R
import com.android.companieshousestreaming.databinding.FragmentStreamBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StreamFragment : Fragment(R.layout.fragment_stream) {

    private lateinit var binding: FragmentStreamBinding
    private val companiesViewModel: CompaniesViewModel by viewModels()

    @OptIn(
        ExperimentalFoundationApi::class,
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStreamBinding.bind(view)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                val companyList = companiesViewModel.companyList
                val connectionStatus = companiesViewModel.connectionStatus

                MaterialTheme {
                    LazyColumn() {

                        stickyHeader {
                            ConnectionStatusMessage(
                                connectionStatus = connectionStatus.value?.status
                            )
                        }

                        items(companyList) { company ->
                            company.data?.companyName?.let { ListItem(companyName = it) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    companyName: String,
) {
    Card(
        modifier = modifier
            .padding(8.dp),
        elevation = 2.dp
    ) {
        Text(text = companyName)
    }
}

@Composable
fun ConnectionStatusMessage(
    modifier: Modifier = Modifier,
    connectionStatus: String?,
){
    Text(text = connectionStatus.toString())
}