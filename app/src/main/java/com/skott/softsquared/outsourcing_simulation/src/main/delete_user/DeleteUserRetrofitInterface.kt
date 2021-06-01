package com.skott.softsquared.outsourcing_simulation.src.main.delete_user

import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import retrofit2.Call
import retrofit2.http.PATCH

interface DeleteUserRetrofitInterface {
    @PATCH("/app/users")
    fun patchDeleteUser(): Call<BaseResponse<String>>
}