package com.bukeke.fakestore

import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import coil.load
import com.bukeke.fakestore.databinding.EpoxyModelProductItemBinding
import com.bukeke.fakestore.epoxy.ViewBindingKotlinModel
import com.bukeke.fakestore.model.domain.Product
import com.bukeke.fakestore.model.ui.UiProduct
import java.text.NumberFormat

data class UiProductEpoxyModel(
    val uiProduct: UiProduct?,
    val onFavoriteIconClicked: (Int) -> Unit
):ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelProductItemBinding.bind() {
        shimmerLayout.isVisible = uiProduct == null
        cardView.isInvisible = uiProduct == null

        uiProduct?.let { uiProduct ->
            shimmerLayout.stopShimmer()
            //setup our text
            productTitleTextView.text = uiProduct.product.title
            productDescriptionTextView.text = uiProduct.product.description
            productCategoryTextView.text = uiProduct.product.category
            productPriceTextView.text = currencyFormatter.format(uiProduct.product.price)

            //favorite icon
            val imageRes = if (uiProduct.isFavorite){
                R.drawable.ic_round_favorite_24
            }else {
                R.drawable.ic_round_favorite_border_24
            }
            favoriteImageView.setIconResource(imageRes)
            favoriteImageView.setOnClickListener {
                onFavoriteIconClicked(uiProduct.product.id)
            }

            //load our image
            productImageViewLoadingProgressBar.isVisible = true
            productImageView.load(
                data = uiProduct.product.image
            ) {
                listener { request, result ->
                    productImageViewLoadingProgressBar.isGone = true
                }
            }
        } ?: shimmerLayout.startShimmer()
    }

}
