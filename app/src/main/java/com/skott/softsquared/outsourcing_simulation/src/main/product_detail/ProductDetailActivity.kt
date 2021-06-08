package com.skott.softsquared.outsourcing_simulation.src.main.product_detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.appbar.AppBarLayout
import com.skott.softsquared.outsourcing_simulation.R
import com.skott.softsquared.outsourcing_simulation.databinding.ProductDetailLayoutBinding
import com.skott.softsquared.outsourcing_simulation.src.config.BaseActivity
import com.skott.softsquared.outsourcing_simulation.src.main.gallery_picker.model.Picture
import com.skott.softsquared.outsourcing_simulation.src.main.product_detail.model.ProductDetailResponse
import com.skott.softsquared.outsourcing_simulation.src.main.profile.ProfileActivity
import com.skott.softsquared.outsourcing_simulation.src.util.lib.SpacesItemDecoration
import java.text.DecimalFormat


class ProductDetailActivity :
    BaseActivity<ProductDetailLayoutBinding>(ProductDetailLayoutBinding::inflate),
    ProductDetailView {
    private var preOffset=0
    private lateinit var context: Context
    private lateinit var viewPager: ViewPager
    private lateinit var sellerProductsAdapter: SmallProductAdapter
    private lateinit var recommendProductAdapter: SmallProductAdapter
    private lateinit var productDetailService: ProductDetailService
    private var productId = -1
    private lateinit var productDetailResponse: ProductDetailResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        productDetailService = ProductDetailService(this)

        binding.backButton.setColorFilter(Color.parseColor("#ffffffff"))
        binding.productDetailShareImageButton.setColorFilter(Color.parseColor("#ffffffff"))
        binding.productDetailMoreImageButton.setColorFilter(Color.parseColor("#ffffffff"))
        setCollapsingToolbarEvent(binding.productDetailMainAppBar)
        setSupportActionBar(binding.mainToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
///      status bar 조절 함수들

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        actionBar?.hide()

        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding.productDetailContentView.setOnScrollChangeListener{ view: View, i: Int, i1: Int, i2: Int, i3: Int ->
            val outValue = IntArray(2)//outValue[0] - x. outValue[1] - y
            binding.productDetailContentTitleTextView.getLocationOnScreen(outValue)
            val hideHeight = binding.mainToolBar.height-binding.productDetailContentTitleTextView.height
            Log.d("Height",hideHeight.toString())
            if(outValue[1]<hideHeight)
                binding.title.text = binding.productDetailContentTitleTextView.text
            else
                binding.title.text = ""
        }
//        window.apply{
//            this.statusBarColor= context.getColor(R.color.pure_trans)
//            decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_FULLSCREEN
//        }
        viewPager = binding.productDetailImageSlider.getViewPager()

        val intentValue = intent.getIntExtra(
            context.getString(R.string.home_activity_to_product_detail_activity_intent_key),
            -1
        )

        if (intentValue == -1)
            Log.e("intent error", "")
        else
            productDetailService.tryGetProductDetail(intentValue)
        setToolbarEvent()
        setBackButtonEvent(binding.backButton)
        setFavoriteEvent(binding.productDetailFavoriteImageButton)
        setProfileIntentEvent(binding.productDetailUserLayout)
    }
    private fun setProfileIntentEvent(layout:ViewGroup)
    {
        layout.setOnClickListener{
            val intent = Intent(context,ProfileActivity::class.java)
            intent.putExtra(context.getString(R.string.profile_intent_key),productDetailResponse.idx)
            startActivity(intent)
        }
    }
    private fun setFavoriteEvent(checkBox: CheckBox)
    {
        checkBox.setOnClickListener{
                productDetailService.tryGetFavoriteProduct(productId)
        }
    }
    fun setToolbarEvent() {
        setSupportActionBar(binding.mainToolBar)
        binding.mainToolBar.setNavigationOnClickListener {
            onBackPressed()
        }

    }
    private fun setCollapsingToolbarEvent(appBarLayout: AppBarLayout)
    {
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val constValue = 51.5
            var calcul = 16+verticalOffset / constValue.toInt()
            Log.d("result",calcul.toString())
            if(calcul<=0||15<=calcul)
                return@OnOffsetChangedListener

            val hexValue = Integer.toString(calcul,16)
            val result = "#ff$hexValue$hexValue$hexValue$hexValue$hexValue$hexValue"
            Log.d("hexColor",result)
            binding.backButton.colorFilter = null
            binding.productDetailShareImageButton.colorFilter = null
            binding.productDetailMoreImageButton.colorFilter = null
            binding.backButton.setColorFilter(Color.parseColor(result),PorterDuff.Mode.SRC_ATOP)
            binding.productDetailShareImageButton.setColorFilter(Color.parseColor(result),PorterDuff.Mode.SRC_ATOP)
            binding.productDetailMoreImageButton.setColorFilter(Color.parseColor(result),PorterDuff.Mode.SRC_ATOP)

//
//            //TODO text 가려짐 감지.
//            //965 how...?
//            Log.d("offset",verticalOffset.toString())
//            binding.title.isInvisible = verticalOffset > -700
            preOffset=verticalOffset
        }
        )
    }
    override fun onGetProductDetailSuccess(productDetailResponse: ProductDetailResponse) {
        /*
        //TODO 아직 처리안한 항목
          sellerMannerTemperature	float	Y	36.5		판매자 매너온도
          status	String	Y	ONSALE		글 상태
          chatNum	int	Y	10		채팅수
          isLiked	String	Y	NO		좋아요 여부
         */
        this.productDetailResponse = productDetailResponse
        productId = productDetailResponse.idx
        if (productDetailResponse.pictures != null && productDetailResponse.pictures.size > 0)
            setImageToViewPager(productDetailResponse.pictures)
        binding.productDetailContentTitleTextView.text = productDetailResponse.title

//        binding.title.text = productDetailResponse.title
//        binding.title.isInvisible=true
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
        binding.productDetailFavoriteImageButton.isChecked = productDetailResponse.isLiked.equals("YES")
        binding.productDetailSellerOtherProductTextView.text =
            context.getString(R.string.product_detail_seller_item)
                .replace("name", productDetailResponse.sellerNickname)

            if (productDetailResponse.isNegotiable.equals("YES")) binding.productDetailIsDealTextView.text =SpannableString(
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
            } else {
                binding.productDetailIsDealTextView.text = context.getString(R.string.product_detail_not_deal)
                binding.productDetailIsDealTextView.setTextColor(context.getColor(R.color.not_deal_color))
            }
        binding.productDetailPriceTextView.text = context.getString(R.string.product_detail_price)
            .replace("price", DecimalFormat("###,###").format(productDetailResponse.price.toInt()))

//        sellerProductsRecyclerView
        val sellerProductsRecyclerView = binding.productDetailSellerOtherProductRecyclerView.getRecyclerView()
        val gridLayoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
        }
        sellerProductsRecyclerView.layoutManager = gridLayoutManager
        sellerProductsRecyclerView.addItemDecoration(SpacesItemDecoration(2, 20, false))
        sellerProductsAdapter =
            SmallProductAdapter(
                context,
                productDetailResponse.sellerItems,
                binding.productDetailSellerOtherProductRecyclerView
            )
        sellerProductsRecyclerView.adapter = sellerProductsAdapter

//        recommendProductsRecyclerView
        binding.productDetailRecommendTextView.text =
            context.getString(R.string.product_detail_recommend)
                .replace("nickname", productDetailResponse.userNickname)
        val recommendProductRecyclerView = binding.productDetailRecommendRecyclerView.getRecyclerView()
        recommendProductRecyclerView.layoutManager = GridLayoutManager(context, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return 1
                }
            }
        }
        recommendProductRecyclerView.addItemDecoration(SpacesItemDecoration(2, 20, false))
        recommendProductAdapter =
            SmallProductAdapter(
                context,
                productDetailResponse.recommendedItems,
                binding.productDetailRecommendRecyclerView
            )
        recommendProductRecyclerView.adapter = recommendProductAdapter
    }

    override fun onGetProductDetailFailure(message: String) {
        Log.e("api error", message)
        showCustomToast("상품 정보를 불러올 수 없습니다.")
        finish()
    }

    override fun onGetFavoriteProductSuccess() {
        //TODO 커스텀 토스트 메세지
        if(binding.productDetailFavoriteImageButton.isChecked)
            showCustomToast(context.getString(R.string.product_detail_add_favorite))
    }

    override fun onGetFavoriteProductFailure(message: String) {
        Log.e("api error", message)
        showCustomToast("관심목록 등록에 실패 하였습니다.")
    }

    private fun setImageToViewPager(picture: ArrayList<Picture>) {

        val imageArray = ArrayList<String>()
        for (item in picture) {
            Log.d("picture", item.pictureUrl)
            imageArray.add(item.pictureUrl)
        }
        val adapter = ImageViewPagerAdapter(context, imageArray)
        viewPager.adapter = adapter
        viewPager.clipToPadding = false
        val wormDotsIndicator = binding.productDetailImageSlider.getDotIndication()
        wormDotsIndicator.setViewPager(viewPager)

    }

    private fun setBackButtonEvent(button: ImageButton) {
        button.setOnClickListener {
            finish()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("location","${event!!.x}, ${event.y}")
        return false
    }
}