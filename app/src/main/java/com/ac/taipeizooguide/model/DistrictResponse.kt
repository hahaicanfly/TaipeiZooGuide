package com.ac.taipeizooguide.model

import com.google.gson.annotations.SerializedName

/**
 * Created on 2021/3/9.
 */
data class DistrictResponse(
    val result: DistrictResult
)

data class DistrictResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    @SerializedName("results")
    val districtList: List<District>
)

data class District(
    @SerializedName("E_Pic_URL")
    val picUrl: String,
    @SerializedName("E_Category")
    val category: String,
    @SerializedName("E_Name")
    val districtName: String,
    @SerializedName("E_Info")
    val info: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("E_Memo")
    val memo: String,
    @SerializedName("E_URL")
    val url: String

)
