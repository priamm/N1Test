package com.priamm.n1test.ui.offer

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.priamm.n1test.R
import com.priamm.n1test.di.Injectable
import com.priamm.n1test.extensions.visible
import com.priamm.n1test.model.Offer
import com.priamm.n1test.ui.adapters.PhotoAdapter
import kotlinx.android.synthetic.main.fragment_offer.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class OfferFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModel: OfferViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val params = OfferFragmentArgs.fromBundle(arguments!!)
        viewModel.init(params.offer)
        viewModel.offer.observe(this, Observer {
            setOffer(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setOffer(offer: Offer) {
        price.text = resources.getString(
            R.string.price,
            NumberFormat.getInstance(Locale.getDefault()).format(offer.params.price)
        )

        val address = offer.params.address?.firstOrNull()
        val street = address?.street?.nameRu
        val houseNumber = address?.houseNumber

        if (TextUtils.isEmpty(street) && TextUtils.isEmpty(houseNumber)) {
            location.text = resources.getString(R.string.address_not_found)
            toolbar.title = resources.getString(R.string.address_not_found)
        } else {
            location.text = resources.getString(R.string.address, street, houseNumber)
            toolbar.title = resources.getString(R.string.address, street, houseNumber)
        }

        val floorIsVisible = offer.params.floor != null && offer.params.floorsCount != null
        floor.visible(floorIsVisible)
        if (floorIsVisible) {
            floor.text = resources.getString(R.string.floor, offer.params.floor, offer.params.floorsCount)
        }

        setParam(kitchen, R.string.kitchenArea, offer.params.kitchenArea)
        setParam(total, R.string.totalArea, offer.params.totalArea)
        setParam(living, R.string.livingArea, offer.params.livingArea)

        image.visible(offer.photos.isEmpty())
        if (offer.photos.isNotEmpty()) {
            photo_pager.clearOnPageChangeListeners()
            photo_pager.adapter = PhotoAdapter(offer.photos.map { it.preview })
            photo_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    image_position.text = resources.getString(R.string.photoCounter, position + 1, offer.photos.size)
                }
            })
            image_position.text =
                resources.getString(R.string.photoCounter, photo_pager.currentItem + 1, offer.photos.size)
        }

    }

    private fun setParam(view: TextView, resourceId: Int, value: Int?) {
        view.visible(value != null)
        if (value != null) {
            view.text = resources.getString(resourceId, value / 100)
        }
    }
}
