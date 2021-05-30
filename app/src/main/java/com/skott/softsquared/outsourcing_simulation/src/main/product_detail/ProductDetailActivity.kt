package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductDetailLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.ProductDetailResponse
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.SmallProduct
import java.text.DecimalFormat

class ProductDetailActivity :
    BaseActivity<ProductDetailLayoutBinding>(ProductDetailLayoutBinding::inflate),
    ProductDetailView {
    private lateinit var context: Context
    private lateinit var viewPager: ViewPager
    private lateinit var smallProductAdapter: SmallProductAdapter
    private lateinit var productDetailService: ProductDetailService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        productDetailService = ProductDetailService(this)
        viewPager = binding.productDetailImageSlider.getViewPager()
        setToolbarEvent()
        val intentValue = intent.getIntExtra(
            context.getString(R.string.home_activity_to_product_detail_activity_intent_key),
            -1
        )
        if (intentValue == -1)
            Log.e("intent error", "")
        else
            productDetailService.tryGetProductDetail(intentValue)
    }

    fun setToolbarEvent() {
        setSupportActionBar(binding.mainToolBar)
        binding.mainToolBar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onGetProductDetailSuccess(productDetailResponse: ProductDetailResponse) {
        /*//TODO 아직 처리안한 항목
  idx	int	Y	1		상품 식별자
  sellerIdx	int	Y	1		판매자 식별자
  sellerMannerTemperature	float	Y	36.5		판매자 매너온도
  status	String	Y	ONSALE		글 상태
  chatNum	int	Y	10		채팅수
  price	int	N	100000		가격
  isLiked	String	Y	NO		좋아요 여부
  sellerItems	Array	N			판매자 판매상품들
  recommendItems	Array	N			추천상품들
  userNickname	String	Y	당근마켓이용자		사용자 닉네임 (화면을 보고 있는 이용자)
         */
        if (productDetailResponse.pictures.size > 0)
            setImageToViewPager(productDetailResponse.pictures)
        binding.productDetailContentTitleTextView.text = productDetailResponse.title
        binding.productDetailContentFavoriteAndViewCountTextView.text =
            context.getString(R.string.product_detail_favorite_and_view_count)
                .replace("favorite", productDetailResponse.likeNum.toString())
                .replace("view", productDetailResponse.clickNum.toString())
        binding.productDetailContentTextView.text = productDetailResponse.content
        binding.productDetailContentCategoryTextView.text = productDetailResponse.category
        binding.productDetailUserDongTextView.text = productDetailResponse.dong
        binding.productDetailContentPullUpTextView.text =
            if (productDetailResponse.isOnTop.equals("YES")) context.getString(R.string.product_detail_pull_up) else ""
        binding.productDetailContentTimeTextView.text = productDetailResponse.passedTime
        binding.productDetailUserNicknameTextView.text = productDetailResponse.sellerNickname
        binding.productDetailIsDealTextView.text =
            if (productDetailResponse.isNegotiable.equals("YES")) SpannableString(
                context.getString(
                    R.string.product_detail_deal
                )
            ).apply {
                setSpan(
                    UnderlineSpan(),
                    0,
                    context.getString(R.string.product_detail_deal).length,
                    0
                )
            } else context.getString(
                R.string.product_detail_not_deal
            )
        binding.productDetailPriceTextView.text = context.getString(R.string.product_detail_price)
            .replace("price", DecimalFormat("###,###").format(productDetailResponse.price.toInt()))
        val recyclerView = binding.productDetailTogetherSeeRecyclerView.getRecyclerView()
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        smallProductAdapter =
            SmallProductAdapter(
                context,
                productDetailResponse.recommendedItems,
                binding.productDetailTogetherSeeRecyclerView
            )
        recyclerView.adapter = smallProductAdapter

    }

    override fun onGetProductDetailFailure(message: String) {
        Log.e("api error", message)
        showCustomToast("상품 정보를 불러올 수 없습니다.")
        finish()
    }

    private fun setImageToViewPager(pictures: ArrayList<Picture>) {

        val imageArray = ArrayList<String>()
        for (item in pictures) {
            imageArray.add(item.fileUrl)
        }
        val adapter = ImageViewPagerAdapter(context, imageArray)

        viewPager.adapter = adapter
        val dotsIndicator = binding.productDetailImageSlider.getDotIndication()
        viewPager = binding.productDetailImageSlider.getViewPager()
        dotsIndicator.setViewPager(viewPager)
    }
}