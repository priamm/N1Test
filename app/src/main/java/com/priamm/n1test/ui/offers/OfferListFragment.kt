package com.priamm.n1test.ui.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.priamm.n1test.R
import com.priamm.n1test.di.Injectable
import com.priamm.n1test.ui.adapters.OfferAdapter
import com.priamm.n1test.ui.adapters.OfferDiffCallback
import kotlinx.android.synthetic.main.fragment_offer_list.*
import javax.inject.Inject

class OfferListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModel: OfferListViewModel

    private val offerAdapter by lazy {
        OfferAdapter(OfferDiffCallback()) { offer ->
            navController().navigate(OfferListFragmentDirections.showOffer(offer))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offer_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.offers.observe(this, Observer {
            offerAdapter.submitList(it)
        })
        viewModel.isInitLoading.observe(this, Observer {
            swipe_layout.isRefreshing = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        offer_list.layoutManager = LinearLayoutManager(context)
        offer_list.adapter = offerAdapter
        swipe_layout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun navController() = findNavController()
}
