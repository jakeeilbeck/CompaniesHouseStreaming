package com.android.companieshousestreaming.models

import com.google.gson.annotations.SerializedName

data class SearchResultCompany(
    @SerializedName("sic_codes")
    val sicCodes: List<String?>?,
    @SerializedName("undeliverable_registered_office_address")
    val undeliverableRegisteredOfficeAddress: Boolean?,
    @SerializedName("jurisdiction")
    val jurisdiction: String?,
    @SerializedName("accounts")
    val accounts: Accounts?,
    @SerializedName("registered_office_address")
    val registeredOfficeAddress: RegisteredOfficeAddress?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("date_of_creation")
    val dateOfCreation: String?,
    @SerializedName("has_been_liquidated")
    val hasBeenLiquidated: Boolean?,
    @SerializedName("last_full_members_list_date")
    val lastFullMembersListDate: String?,
    @SerializedName("company_number")
    val companyNumber: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("company_name")
    val companyName: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("company_status")
    val companyStatus: String?,
    @SerializedName("has_insolvency_history")
    val hasInsolvencyHistory: Boolean?,
    @SerializedName("has_charges")
    val hasCharges: Boolean?,
    @SerializedName("previous_company_names")
    val previousCompanyNames: List<PreviousCompanyName?>?,
    @SerializedName("confirmation_statement")
    val confirmationStatement: ConfirmationStatement?,
    @SerializedName("links")
    val links: Links?,
    @SerializedName("registered_office_is_in_dispute")
    val registeredOfficeIsInDispute: Boolean?,
    @SerializedName("has_super_secure_pscs")
    val hasSuperSecurePscs: Boolean?,
    @SerializedName("can_file")
    val canFile: Boolean?
) {
    data class Accounts(
        @SerializedName("accounting_reference_date")
        val accountingReferenceDate: AccountingReferenceDate?,
        @SerializedName("overdue")
        val overdue: Boolean?,
        @SerializedName("next_made_up_to")
        val nextMadeUpTo: String?,
        @SerializedName("last_accounts")
        val lastAccounts: LastAccounts?,
        @SerializedName("next_accounts")
        val nextAccounts: NextAccounts?,
        @SerializedName("next_due")
        val nextDue: String?
    ) {
        data class AccountingReferenceDate(
            @SerializedName("month")
            val month: String?,
            @SerializedName("day")
            val day: String?
        )

        data class LastAccounts(
            @SerializedName("type")
            val type: String?,
            @SerializedName("made_up_to")
            val madeUpTo: String?,
            @SerializedName("period_end_on")
            val periodEndOn: String?,
            @SerializedName("period_start_on")
            val periodStartOn: String?
        )

        data class NextAccounts(
            @SerializedName("period_start_on")
            val periodStartOn: String?,
            @SerializedName("due_on")
            val dueOn: String?,
            @SerializedName("period_end_on")
            val periodEndOn: String?,
            @SerializedName("overdue")
            val overdue: Boolean?
        )
    }

    data class RegisteredOfficeAddress(
        @SerializedName("locality")
        val locality: String?,
        @SerializedName("address_line_2")
        val addressLine2: String?,
        @SerializedName("address_line_1")
        val addressLine1: String?,
        @SerializedName("postal_code")
        val postalCode: String?,
        @SerializedName("region")
        val region: String?
    )

    data class PreviousCompanyName(
        @SerializedName("name")
        val name: String?,
        @SerializedName("effective_from")
        val effectiveFrom: String?,
        @SerializedName("ceased_on")
        val ceasedOn: String?
    )

    data class ConfirmationStatement(
        @SerializedName("last_made_up_to")
        val lastMadeUpTo: String?,
        @SerializedName("next_due")
        val nextDue: String?,
        @SerializedName("overdue")
        val overdue: Boolean?,
        @SerializedName("next_made_up_to")
        val nextMadeUpTo: String?
    )

    data class Links(
        @SerializedName("self")
        val self: String?,
        @SerializedName("filing_history")
        val filingHistory: String?,
        @SerializedName("officers")
        val officers: String?,
        @SerializedName("charges")
        val charges: String?,
        @SerializedName("persons_with_significant_control")
        val personsWithSignificantControl: String?
    )
}