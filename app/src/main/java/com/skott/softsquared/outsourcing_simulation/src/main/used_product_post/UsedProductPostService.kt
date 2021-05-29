package com.skott.softsquared.outsourcing_simulation.src.main.used_product_post

import com.skott.softsquared.outsourcing_simulation.src.config.ApplicationClass
import com.skott.softsquared.outsourcing_simulation.src.config.BaseResponse
import com.skott.softsquared.outsourcing_simulation.src.main.used_product_post.model.UsedProductPostRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsedProductPostService(val view:UsedProductPostActivity){
    fun tryPostItemUpload(usedProductPostRequest: UsedProductPostRequest)
    {
        val api = ApplicationClass.sRetrofit.create(UsedProductPostRetrofitInterface::class.java)
        api.postItemUpload(usedProductPostRequest).enqueue(object: Callback<BaseResponse<String>> {
            override fun onResponse(
                call: Call<BaseResponse<String>>,
                response: Response<BaseResponse<String>>
            ) {
                view.onPostItemUploadSuccess()
            }

            override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                view.onPostItemUploadFailure(t.message?:"게시글 업로드 중 에러 발생")
            }

        })
    }
}