package com.kodeplay.habittracker2
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kodeplay.habittracker2.databinding.FragmentCounterDetailBinding
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [CounterDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CounterDetailFragment : Fragment() {
    private lateinit var counterDetailViewModel: CounterDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCounterDetailBinding>(inflater,R.layout.fragment_counter_detail,container,false)
        val application = requireNotNull(this.activity).application
        val counterViewModelFactory = CounterViewModelFactory(application);
        counterDetailViewModel = ViewModelProvider(this,counterViewModelFactory).get (CounterDetailViewModel::class.java)
        counterDetailViewModel.counterData.observe (viewLifecycleOwner,Observer {
        newCounterdata ->
            binding.counterDataText.text = newCounterdata.toString ()
        })

//        counterDetailViewModel.status.observe (viewLifecycleOwner,Observer {
//                newStatus ->
//            binding.apiStatusText.text = newStatus.toString ()
//        })

//        counterDetailViewModel.counterDatabaseData.observe (viewLifecycleOwner,Observer {
//                newCounterDatabaseData ->
//            binding.counterDatabaseDataText.text = newCounterDatabaseData.toString ()
//        })
        binding.counterUpdateButton.setOnClickListener { counterDetailViewModel.updateCount() }
        return binding.root
    }


}