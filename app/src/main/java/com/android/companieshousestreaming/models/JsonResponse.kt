package com.android.companieshousestreaming.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class JsonResponse(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "event")
    val event: Event,
    @Json(name = "resource_id")
    val resourceId: String,
    @Json(name = "resource_kind")
    val resourceKind: String,
    @Json(name = "resource_uri")
    val resourceUri: String
) {

    data class Data(
        @Json(name = "accounts")
        val accounts: Accounts,
        @Json(name = "annual_return")
        val annualReturn: AnnualReturn,
        @Json(name = "branch_company_details")
        val branchCompanyDetails: BranchCompanyDetails,
        @Json(name = "can_file")
        val canFile: String,
        @Json(name = "company_name")
        val companyName: String,
        @Json(name = "company_number")
        val companyNumber: String,
        @Json(name = "company_status")
        val companyStatus: String,
        @Json(name = "company_status_detail")
        val companyStatusDetail: String,
        @Json(name = "confirmation_statement")
        val confirmationStatement: ConfirmationStatement,
        @Json(name = "date_of_cessation")
        val dateOfCessation: String,
        @Json(name = "date_of_creation")
        val dateOfCreation: String,
        @Json(name = "etag")
        val etag: String,
        @Json(name = "foreign_company_details")
        val foreignCompanyDetails: ForeignCompanyDetails,
        @Json(name = "has_been_liquidated")
        val hasBeenLiquidated: String,
        @Json(name = "has_charges")
        val hasCharges: String,
        @Json(name = "has_insolvency_history")
        val hasInsolvencyHistory: String,
        @Json(name = "is_community_interest_company")
        val isCommunityInterestCompany: String,
        @Json(name = "jurisdiction")
        val jurisdiction: String,
        @Json(name = "last_full_members_list_date")
        val lastFullMembersListDate: String,
        @Json(name = "links")
        val links: Links,
        @Json(name = "previous_company_names")
        val previousCompanyNames: List<PreviousCompanyName>,
        @Json(name = "registered_office_address")
        val registeredOfficeAddress: RegisteredOfficeAddress,
        @Json(name = "registered_office_is_in_dispute")
        val registeredOfficeIsInDispute: String,
        @Json(name = "sic_codes")
        val sicCodes: List<String>,
        @Json(name = "type")
        val type: String,
        @Json(name = "undeliverable_registered_office_address")
        val undeliverableRegisteredOfficeAddress: String
    ) {

        data class Accounts(
            @Json(name = "accounting_reference_date")
            val accountingReferenceDate: AccountingReferenceDate,
            @Json(name = "last_accounts")
            val lastAccounts: LastAccounts,
            @Json(name = "next_due")
            val nextDue: String,
            @Json(name = "next_made_up_to")
            val nextMadeUpTo: String,
            @Json(name = "overdue")
            val overdue: String
        ) {

            data class AccountingReferenceDate(
                @Json(name = "day")
                val day: String,
                @Json(name = "month")
                val month: String
            )


            data class LastAccounts(
                @Json(name = "made_up_to")
                val madeUpTo: String,
                @Json(name = "type")
                val type: Type
            ) {
                @JsonClass(generateAdapter = true)
                class Type(
                )
            }
        }


        data class AnnualReturn(
            @Json(name = "last_made_up_to")
            val lastMadeUpTo: String,
            @Json(name = "next_due")
            val nextDue: String,
            @Json(name = "next_made_up_to")
            val nextMadeUpTo: String,
            @Json(name = "overdue")
            val overdue: String
        )


        data class BranchCompanyDetails(
            @Json(name = "business_activity")
            val businessActivity: String,
            @Json(name = "parent_company_name")
            val parentCompanyName: String,
            @Json(name = "parent_company_number")
            val parentCompanyNumber: String
        )


        data class ConfirmationStatement(
            @Json(name = "last_made_up_to")
            val lastMadeUpTo: String,
            @Json(name = "next_due")
            val nextDue: String,
            @Json(name = "next_made_up_to")
            val nextMadeUpTo: String,
            @Json(name = "overdue")
            val overdue: String
        )


        data class ForeignCompanyDetails(
            @Json(name = "accounting_requirement")
            val accountingRequirement: AccountingRequirement,
            @Json(name = "accounts")
            val accounts: Accounts,
            @Json(name = "business_activity")
            val businessActivity: String,
            @Json(name = "company_type")
            val companyType: String,
            @Json(name = "governed_by")
            val governedBy: String,
            @Json(name = "is_a_credit_finance_institution")
            val isACreditFinanceInstitution: String,
            @Json(name = "originating_registry")
            val originatingRegistry: OriginatingRegistry,
            @Json(name = "registration_number")
            val registrationNumber: String
        ) {

            data class AccountingRequirement(
                @Json(name = "foreign_account_type")
                val foreignAccountType: String,
                @Json(name = "terms_of_account_publication")
                val termsOfAccountPublication: String
            )


            data class Accounts(
                @Json(name = "account_period_from:")
                val accountPeriodFrom: AccountPeriodFrom,
                @Json(name = "account_period_to")
                val accountPeriodTo: AccountPeriodTo,
                @Json(name = "must_file_within")
                val mustFileWithin: MustFileWithin
            ) {

                data class AccountPeriodFrom(
                    @Json(name = "day")
                    val day: String,
                    @Json(name = "month")
                    val month: String
                )


                data class AccountPeriodTo(
                    @Json(name = "day")
                    val day: String,
                    @Json(name = "month")
                    val month: String
                )


                data class MustFileWithin(
                    @Json(name = "months")
                    val months: String
                )
            }


            data class OriginatingRegistry(
                @Json(name = "country")
                val country: String,
                @Json(name = "name")
                val name: String
            )
        }


        data class Links(
            @Json(name = "persons_with_significant_control")
            val personsWithSignificantControl: String,
            @Json(name = "persons_with_significant_control_statements")
            val personsWithSignificantControlStatements: String,
            @Json(name = "registers")
            val registers: String,
            @Json(name = "self")
            val self: String
        )


        data class PreviousCompanyName(
            @Json(name = "ceased_on")
            val ceasedOn: String,
            @Json(name = "effective_from")
            val effectiveFrom: String,
            @Json(name = "name")
            val name: String
        )


        data class RegisteredOfficeAddress(
            @Json(name = "address_line_1")
            val addressLine1: String,
            @Json(name = "address_line_2")
            val addressLine2: String,
            @Json(name = "care_of")
            val careOf: String,
            @Json(name = "country")
            val country: String,
            @Json(name = "locality")
            val locality: String,
            @Json(name = "po_box")
            val poBox: String,
            @Json(name = "postal_code")
            val postalCode: String,
            @Json(name = "premises")
            val premises: String,
            @Json(name = "region")
            val region: String
        )
    }

    
    data class Event(
        @Json(name = "fields_changed")
        val fieldsChanged: List<String>,
        @Json(name = "published_at")
        val publishedAt: String,
        @Json(name = "timepoint")
        val timepoint: String,
        @Json(name = "type")
        val type: String
    )
}