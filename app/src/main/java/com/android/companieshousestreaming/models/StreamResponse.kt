package com.android.companieshousestreaming.models

import com.google.gson.annotations.SerializedName

data class StreamResponse(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("event")
    val event: Event?,
    @SerializedName("resource_id")
    val resourceId: String?,
    @SerializedName("resource_kind")
    val resourceKind: String?,
    @SerializedName("resource_uri")
    val resourceUri: String?
) {
    data class Data(
        @SerializedName("accounts")
        val accounts: Accounts?,
        @SerializedName("annual_return")
        val annualReturn: AnnualReturn?,
        @SerializedName("branch_company_details")
        val branchCompanyDetails: BranchCompanyDetails?,
        @SerializedName("can_file")
        val canFile: Boolean?,
        @SerializedName("company_name")
        val companyName: String?,
        @SerializedName("company_number")
        val companyNumber: String?,
        @SerializedName("company_status")
        val companyStatus: String?,
        @SerializedName("company_status_detail")
        val companyStatusDetail: String?,
        @SerializedName("confirmation_statement")
        val confirmationStatement: ConfirmationStatement?,
        @SerializedName("date_of_cessation")
        val dateOfCessation: String?,
        @SerializedName("date_of_creation")
        val dateOfCreation: String?,
        @SerializedName("etag")
        val etag: String?,
        @SerializedName("foreign_company_details")
        val foreignCompanyDetails: ForeignCompanyDetails?,
        @SerializedName("has_been_liquidated")
        val hasBeenLiquidated: Boolean?,
        @SerializedName("has_charges")
        val hasCharges: Boolean?,
        @SerializedName("has_insolvency_history")
        val hasInsolvencyHistory: Boolean?,
        @SerializedName("is_community_interest_company")
        val isCommunityInterestCompany: Boolean?,
        @SerializedName("jurisdiction")
        val jurisdiction: String?,
        @SerializedName("last_full_members_list_date")
        val lastFullMembersListDate: String?,
        @SerializedName("links")
        val links: Links?,
        @SerializedName("previous_company_names")
        val previousCompanyNames: List<PreviousCompanyName?>?,
        @SerializedName("registered_office_address")
        val registeredOfficeAddress: RegisteredOfficeAddress?,
        @SerializedName("registered_office_is_in_dispute")
        val registeredOfficeIsInDispute: Boolean?,
        @SerializedName("sic_codes")
        val sicCodes: List<String?>?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("undeliverable_registered_office_address")
        val undeliverableRegisteredOfficeAddress: Boolean?
    ) {
        data class Accounts(
            @SerializedName("accounting_reference_date")
            val accountingReferenceDate: AccountingReferenceDate?,
            @SerializedName("last_accounts")
            val lastAccounts: LastAccounts?,
            @SerializedName("next_accounts")
            val nextAccounts: NextAccounts?,
            @SerializedName("next_due")
            val nextDue: String?,
            @SerializedName("next_made_up_to")
            val nextMadeUpTo: String?,
            @SerializedName("overdue")
            val overdue: Boolean?
        ) {
            data class AccountingReferenceDate(
                @SerializedName("day")
                val day: String?,
                @SerializedName("month")
                val month: String?
            )

            data class LastAccounts(
                @SerializedName("made_up_to")
                val madeUpTo: String?,
                @SerializedName("period_end_on")
                val periodEndOn: String?,
                @SerializedName("period_start_on")
                val periodStartOn: String?,
                @SerializedName("type")
                val type: String?
            )

            data class NextAccounts(
                @SerializedName("due_on")
                val dueOn: String?,
                @SerializedName("period_end_on")
                val periodEndOn: String?,
                @SerializedName("period_start_on")
                val periodStartOn: String?
            )
        }

        data class AnnualReturn(
            @SerializedName("last_made_up_to")
            val lastMadeUpTo: String?,
            @SerializedName("next_due")
            val nextDue: String?,
            @SerializedName("next_made_up_to")
            val nextMadeUpTo: String?,
            @SerializedName("overdue")
            val overdue: Boolean?
        )

        data class BranchCompanyDetails(
            @SerializedName("business_activity")
            val businessActivity: String?,
            @SerializedName("parent_company_name")
            val parentCompanyName: String?,
            @SerializedName("parent_company_number")
            val parentCompanyNumber: String?
        )

        data class ConfirmationStatement(
            @SerializedName("last_made_up_to")
            val lastMadeUpTo: String?,
            @SerializedName("next_due")
            val nextDue: String?,
            @SerializedName("next_made_up_to")
            val nextMadeUpTo: String?,
            @SerializedName("overdue")
            val overdue: Boolean?
        )

        data class ForeignCompanyDetails(
            @SerializedName("accounting_requirement")
            val accountingRequirement: AccountingRequirement?,
            @SerializedName("accounts")
            val accounts: Accounts?,
            @SerializedName("business_activity")
            val businessActivity: String?,
            @SerializedName("company_type")
            val companyType: String?,
            @SerializedName("governed_by")
            val governedBy: String?,
            @SerializedName("is_a_credit_finance_institution")
            val isACreditFinanceInstitution: Boolean?,
            @SerializedName("originating_registry")
            val originatingRegistry: OriginatingRegistry?,
            @SerializedName("registration_number")
            val registrationNumber: String?
        ) {
            data class AccountingRequirement(
                @SerializedName("foreign_account_type")
                val foreignAccountType: String?,
                @SerializedName("terms_of_account_publication")
                val termsOfAccountPublication: String?
            )

            data class Accounts(
                @SerializedName("account_period_from:")
                val accountPeriodFrom: AccountPeriodFrom?,
                @SerializedName("account_period_to")
                val accountPeriodTo: AccountPeriodTo?,
                @SerializedName("must_file_within")
                val mustFileWithin: MustFileWithin?
            ) {
                data class AccountPeriodFrom(
                    @SerializedName("day")
                    val day: String?,
                    @SerializedName("month")
                    val month: String?
                )

                data class AccountPeriodTo(
                    @SerializedName("day")
                    val day: String?,
                    @SerializedName("month")
                    val month: String?
                )

                data class MustFileWithin(
                    @SerializedName("months")
                    val months: String?
                )
            }

            data class OriginatingRegistry(
                @SerializedName("country")
                val country: String?,
                @SerializedName("name")
                val name: String?
            )
        }

        data class Links(
            @SerializedName("persons_with_significant_control")
            val personsWithSignificantControl: String?,
            @SerializedName("persons_with_significant_control_statements")
            val personsWithSignificantControlStatements: String?,
            @SerializedName("registers")
            val registers: String?,
            @SerializedName("self")
            val self: String?,
            @SerializedName("filing_history")
            val filingHistory: String?,
            @SerializedName("officers")
            val officers: String?,
            @SerializedName("charges")
            val charges: String?
        )

        data class PreviousCompanyName(
            @SerializedName("ceased_on")
            val ceasedOn: String?,
            @SerializedName("effective_from")
            val effectiveFrom: String?,
            @SerializedName("name")
            val name: String?
        )

        data class RegisteredOfficeAddress(
            @SerializedName("address_line_1")
            val addressLine1: String?,
            @SerializedName("address_line_2")
            val addressLine2: String?,
            @SerializedName("care_of")
            val careOf: String?,
            @SerializedName("country")
            val country: String?,
            @SerializedName("locality")
            val locality: String?,
            @SerializedName("po_box")
            val poBox: String?,
            @SerializedName("postal_code")
            val postalCode: String?,
            @SerializedName("premises")
            val premises: String?,
            @SerializedName("region")
            val region: String?
        )
    }

    data class Event(
        @SerializedName("fields_changed")
        val fieldsChanged: List<String?>?,
        @SerializedName("published_at")
        val publishedAt: String?,
        @SerializedName("timepoint")
        val timepoint: Int?,
        @SerializedName("type")
        val type: String?
    )
}