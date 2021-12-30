package com.android.companieshousestreaming.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
                    LazyColumn {
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
                        item {
                            InfoItem(
                                heading = "Company Number",
                                infoList = listOf(selectedCompany?.data?.companyNumber.toString())
                            )
                        }
                        item {
                            InfoItem(
                                heading = "Trading Dates",
                                infoList = listOf(
                                    "From ${selectedCompany?.data?.dateOfCreation.toString()} to ${selectedCompany?.data?.dateOfCessation ?: "current"}"
                                )
                            )
                        }
                        item {
                            if (!selectedCompany?.data?.registeredOfficeAddress?.region.isNullOrBlank()) {
                                InfoItem(
                                    heading = "Registered Office Region",
                                    infoList = listOf(selectedCompany?.data?.registeredOfficeAddress?.region.toString())
                                )
                            }
                        }
                        item {
                            val links = listOf(
                                Pair("Company", selectedCompany?.data?.links?.self),
                                Pair("Registers", selectedCompany?.data?.links?.registers),
                                Pair(
                                    "Persons With Significant Control",
                                    selectedCompany?.data?.links?.personsWithSignificantControl
                                ),
                                Pair(
                                    "Persons With Significant Control Statements",
                                    selectedCompany?.data?.links?.personsWithSignificantControlStatements
                                ),
                                Pair("Officers", selectedCompany?.data?.links?.officers),
                                Pair("Filing History", selectedCompany?.data?.links?.filingHistory)
                            )

                            InfoItemLinks(
                                heading = "Links",
                                infoList = links,
                                itemClick = { openLinkIntent(it) }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun openLinkIntent(url: String) {
        val fullLink = "https://find-and-update.company-information.service.gov.uk$url"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(fullLink)))
    }
}

@Composable
fun InfoItemLinks(
    modifier: Modifier = Modifier,
    heading: String,
    infoList: List<Pair<String, String?>>,
    itemClick: (String) -> Unit,
) {
    Card(
        modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
    ) {
        Column {
            Heading(text = heading)
            infoList.forEach {
                if (!it.second.isNullOrBlank()) {
                    InfoRow(
                        modifier = modifier.clickable { itemClick(it.second!!) },
                        heading = it.first,
                        info = "find-and-update.company-information.service.gov.uk${it.second}",
                        isLink = true,
                    )
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
            if (infoList.size > 1) {
                Heading(text = heading)
                infoList.forEach {
                    Info(text = it, isLink = false)
                }
            } else {
                infoList.forEach {
                    InfoRow(
                        heading = heading,
                        info = it,
                        isLink = false,
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
    isLink: Boolean,
) {
    Column(
        modifier
    ) {
        Heading(text = heading)
        Info(text = info, isLink = isLink)
    }
}

@Composable
fun Heading(
    modifier: Modifier = Modifier,
    text: String,
) {

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
    isLink: Boolean,
) {
    if (isLink) {
        Text(
            text = text,
            modifier = modifier
                .padding(8.dp),
            color = androidx.compose.ui.graphics.Color.Blue,
            textDecoration = TextDecoration.Underline,
        )
    } else {
        Text(
            text = text,
            modifier
                .padding(8.dp)
        )
    }
}