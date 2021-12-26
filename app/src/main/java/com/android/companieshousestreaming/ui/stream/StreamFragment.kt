package com.android.companieshousestreaming.ui.stream

import android.os.Bundle
import android.view.View
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.android.companieshousestreaming.R
import com.android.companieshousestreaming.databinding.FragmentStreamBinding
import com.android.companieshousestreaming.models.JsonResponse
import com.android.companieshousestreaming.ui.CompaniesViewModel
import com.android.companieshousestreaming.ui.StreamConnectionStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StreamFragment : Fragment(R.layout.fragment_stream) {

    private lateinit var binding: FragmentStreamBinding
    private val companiesViewModel: CompaniesViewModel by activityViewModels()

    @OptIn(
        ExperimentalFoundationApi::class,
        ExperimentalAnimationApi::class
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
                    LazyColumn {

                        stickyHeader {
                            ConnectionStatusMessage(
                                connectionStatus = connectionStatus.value?.status
                            )
                        }

                        item {
                            RetryButton(
                                connectionStatus = connectionStatus.value?.status,
                                retryClick = { companiesViewModel.getStream() },
                            )
                        }

                        items(companyList) { company ->
                            ListItem(
                                company = company,
                                itemClick = {
                                    companiesViewModel.selectedCompany = it
                                    view.findNavController().navigate(
                                            StreamFragmentDirections.actionStreamFragmentToDetailFragment()
                                        )
                                },
                            )
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
    company: JsonResponse,
    itemClick: (JsonResponse) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { itemClick(company) },
        elevation = 2.dp,
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
        ) {
            Text(text = company.data?.companyName ?: "", fontWeight = FontWeight.SemiBold)
            Text(text = "Previous Company Names: ${company.data?.previousCompanyNames?.size ?: 0}")
            Text(text = "Year Started Trading: ${company.data?.dateOfCreation?.substring(0,4)}")
            Text(text = company.data?.branchCompanyDetails?.businessActivity ?: "")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ConnectionStatusMessage(
    modifier: Modifier = Modifier,
    connectionStatus: String?,
) {
    val statusBackground =
        when (connectionStatus) {
            StreamConnectionStatus.Connecting.status -> listOf(Color.Blue, Color.Cyan)
            StreamConnectionStatus.Successful.status -> listOf(Color(0xFF1B5E20), Color.Cyan)
            StreamConnectionStatus.Idle.status -> listOf(Color.Magenta, Color.Cyan)
            else -> listOf(Color.Red, Color.Magenta)
        }
    AnimatedContent(
        targetState = connectionStatus,
        transitionSpec = {
            slideInVertically { height -> height } + fadeIn() with
                    slideOutVertically { height -> -height } + fadeOut()
        },
    ) {

        Row(
            modifier = modifier
                .background(brush = Brush.horizontalGradient(statusBackground))
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = connectionStatus.toString(),
                modifier = modifier
                    .background(Color.Transparent),
                color = Color.White,
            )
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewConnectionStatusMessage() {
    ConnectionStatusMessage(modifier = Modifier, "Connecting")
}

@ExperimentalAnimationApi
@Composable
fun RetryButton(
    modifier: Modifier = Modifier,
    connectionStatus: String?,
    retryClick: () -> Unit,
){
    AnimatedVisibility(
        visible = connectionStatus == StreamConnectionStatus.Unsuccessful.status ||
                connectionStatus == StreamConnectionStatus.Error.status ||
                connectionStatus == StreamConnectionStatus.Failed.status ||
                connectionStatus == StreamConnectionStatus.ResponseNull.status,
    ) {
        Button(
            onClick = { retryClick() },
            modifier = modifier
                .padding(8.dp),
        ) {
            Text(text = "Retry")
        }
    }
}