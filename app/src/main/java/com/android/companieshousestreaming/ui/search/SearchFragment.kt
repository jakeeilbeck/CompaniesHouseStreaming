package com.android.companieshousestreaming.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.android.companieshousestreaming.R
import com.android.companieshousestreaming.databinding.FragmentSearchBinding
import com.android.companieshousestreaming.models.SearchResultList
import com.android.companieshousestreaming.ui.CompaniesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val companiesViewModel: CompaniesViewModel by activityViewModels()

    @OptIn(
        ExperimentalFoundationApi::class,
        ExperimentalAnimationApi::class,
        ExperimentalComposeUiApi::class
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    LazyColumn {
                        item {
                            SearchBar(
                                search = { companiesViewModel.getSearchResults(it) },
                                searchQuery = companiesViewModel.searchQuery
                            )
                        }
                        items(companiesViewModel.searchList) { company ->
                            CompanySearchItem(
                                company = company,
                                selectedCompany = {
                                    companiesViewModel.searchCompany(it)
                                    view.findNavController().navigate(
                                        SearchFragmentDirections.actionSearchFragmentToSearchedCompanyDetailFragment()
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    search: (String) -> Unit,
    searchQuery: MutableState<String>,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Search company...",
                    style = MaterialTheme.typography.subtitle2,
                    fontStyle = FontStyle.Italic
                )
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                searchQuery.value = searchQuery.value.trim()
                search(searchQuery.value)
                focusManager.clearFocus()
                keyboardController?.hide()
            }),
        )
    }
}

@Composable
fun CompanySearchItem(
    modifier: Modifier = Modifier,
    company: SearchResultList.Item?,
    selectedCompany: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 2.dp,
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .clickable {
                    selectedCompany(company?.companyNumber.toString())
                }
        ) {
            Text(text = company?.title.toString(), fontWeight = FontWeight.SemiBold)
            Text(text = "Year Started Trading: ${company?.dateOfCreation?.substring(0, 4)}")
            Text(text = "Registered to: ${company?.addressSnippet}")
        }
    }
}