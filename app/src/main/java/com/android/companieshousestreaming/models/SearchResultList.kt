package com.android.companieshousestreaming.models

import com.google.gson.annotations.SerializedName

data class SearchResultList(
    @SerializedName("start_index")
    val startIndex: Int?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("page_number")
    val pageNumber: Int?,
    @SerializedName("items")
    val items: List<Item?>?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("items_per_page")
    val itemsPerPage: Int?
) {
    data class Item(
        @SerializedName("links")
        val links: Links?,
        @SerializedName("kind")
        val kind: String?,
        @SerializedName("company_type")
        val companyType: String?,
        @SerializedName("address_snippet")
        val addressSnippet: String?,
        @SerializedName("company_number")
        val companyNumber: String?,
        @SerializedName("address")
        val address: Address?,
        @SerializedName("description_identifier")
        val descriptionIdentifier: List<String?>?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("matches")
        val matches: Matches?,
        @SerializedName("snippet")
        val snippet: String?,
        @SerializedName("date_of_creation")
        val dateOfCreation: String?,
        @SerializedName("company_status")
        val companyStatus: String?,
        @SerializedName("date_of_cessation")
        val dateOfCessation: String?
    ) {
        data class Links(
            @SerializedName("self")
            val self: String?
        )

        data class Address(
            @SerializedName("address_line_1")
            val addressLine1: String?,
            @SerializedName("locality")
            val locality: String?,
            @SerializedName("country")
            val country: String?,
            @SerializedName("premises")
            val premises: String?,
            @SerializedName("postal_code")
            val postalCode: String?,
            @SerializedName("region")
            val region: String?,
            @SerializedName("address_line_2")
            val addressLine2: String?
        )

        data class Matches(
            @SerializedName("title")
            val title: List<Int?>?,
            @SerializedName("snippet")
            val snippet: List<Int?>?
        )
    }
}