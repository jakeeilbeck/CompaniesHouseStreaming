package com.android.companieshousestreaming.ui.detail

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.companieshousestreaming.R
import com.android.companieshousestreaming.databinding.FragmentDetailBinding
import com.android.companieshousestreaming.ui.CompaniesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val companiesViewModel: CompaniesViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        val selectedCompany = companiesViewModel.selectedCompany

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    LazyColumn{
                        item {
                            InfoItem(
                                heading = "Company Name",
                                infoList = listOf(selectedCompany?.data?.companyName.toString())
                            )
                        }
                        item {
                            if (!selectedCompany?.data?.previousCompanyNames.isNullOrEmpty()) {
                                val previousNames = mutableListOf<String>()
                                selectedCompany?.data?.previousCompanyNames?.forEach {
                                    previousNames.add(it?.name.toString())
                                }
                                InfoItem(
                                    heading = "Previous Company Name",
                                    infoList = previousNames
                                )
                            }
                        }
                        item{
                            InfoItem(
                                heading = "Company Number",
                                infoList = listOf(selectedCompany?.data?.companyNumber.toString())
                            )
                        }
                        item{
                            InfoItem(
                                heading = "Trading Dates",
                                infoList = listOf(
                                    "From ${selectedCompany?.data?.dateOfCreation.toString()} to ${ selectedCompany?.data?.dateOfCessation?: "current"}"
                                )
                            )
                        }
                        item{
                            if(!selectedCompany?.data?.registeredOfficeAddress?.region.isNullOrBlank()){
                                InfoItem(
                                    heading = "Registered Office Region",
                                    infoList = listOf(selectedCompany?.data?.registeredOfficeAddress?.region.toString())
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoItem(
    modifier: Modifier = Modifier,
    heading: String,
    infoList: List<String>,
) {
    Card(
        modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
    ) {
        Column {
            if (infoList.size > 1){
                Heading(text = heading)
                infoList.forEach {
                    Info(text = it)
                }
            } else {
                infoList.forEach {
                    InfoRow(
                        heading = heading,
                        info = it
                    )
                }
            }
        }
    }
}

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    heading: String,
    info: String,
){
    Column(
        modifier
    ) {
        Heading(text = heading)
        Info(text = info)
    }
}

@Composable
fun Heading(
    modifier: Modifier = Modifier,
    text: String,
){

    Text(
        text = text,
        modifier
            .padding(8.dp),
     fontWeight = FontWeight.Bold,
    )
}

@Composable
fun Info(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        text = text,
        modifier
            .padding(8.dp)
    )
}