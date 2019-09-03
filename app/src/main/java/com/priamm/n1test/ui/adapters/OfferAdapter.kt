package com.priamm.n1test.ui.adapters

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.priamm.n1test.R
import com.priamm.n1test.extensions.inflate
import com.priamm.n1test.extensions.visible
import com.priamm.n1test.model.Offer
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_offer.*
import java.text.NumberFormat
import java.util.*

class OfferAdapter(
    offerDiffCallback: OfferDiffCallback,
    private val clickListener: (Offer) -> Unit = {}
) : PagedListAdapter<Offer, OfferAdapter.OfferViewHolder>(offerDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(parent.inflate(R.layout.item_offer))
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = getItem(position)
        holder.bind(offer!!)
    }

    inner class OfferViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        private val resources = itemView.resources

        fun bind(offer: Offer) {
            itemView.setOnClickListener {
                clickListener(offer)
            }
            price.text = resources.getString(
                R.string.price,
                NumberFormat.getInstance(Locale.getDefault()).format(offer.params.price)
            )

            val address = offer.params.address?.firstOrNull()
            val street = address?.street?.nameRu
            val houseNumber = address?.houseNumber

            if (TextUtils.isEmpty(street) && TextUtils.isEmpty(houseNumber))
                location.text = resources.getString(R.string.address_not_found)
            else
                location.text = resources.getString(R.string.address, street, houseNumber)

            val floorIsVisible = offer.params.floor != null && offer.params.floorsCount != null
            floor.visible(floorIsVisible)
            if (floorIsVisible) {
                floor.text = resources.getString(R.string.floor, offer.params.floor, offer.params.floorsCount)
            }
            bindArea(kitchen, R.string.kitchenArea, offer.params.kitchenArea)
            bindArea(total, R.string.totalArea, offer.params.totalArea)
            bindArea(living, R.string.livingArea, offer.params.livingArea)

            image.visible(offer.photos.isEmpty())
            if (offer.photos.isNotEmpty()) {
                photo_pager.clearOnPageChangeListeners()
                photo_pager.adapter = PhotoAdapter(offer.photos.map { it.preview })
                photo_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(position: Int) {
                        image_position.text =
                            resources.getString(R.string.photoCounter, position + 1, offer.photos.size)
                    }
                })
                image_position.text =
                    resources.getString(R.string.photoCounter, photo_pager.currentItem + 1, offer.photos.size)
            }
        }

        private fun bindArea(view: TextView, resourceId: Int, value: Int?) {
            view.visible(value != null)
            if (value != null) {
                view.text = resources.getString(resourceId, value / 100)
            }
        }
    }


}