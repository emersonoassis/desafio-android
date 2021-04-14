package com.picpay.desafio.android.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.onPostValue
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.ui.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.model.UserBinding
import com.picpay.desafio.android.presentation.viewmodel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var adapter: UserListAdapter

    private val viewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservers() {
        lifecycle.addObserver(viewModel)

        viewModel.userListViewState.onPostValue(
            this,
            onLoading = ::handleLoading,
            onError = { handleError() },
            onSuccess = ::handleSuccess
        )
    }

    private fun handleLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun handleError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(this, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun handleSuccess(users: List<UserBinding>) {
        binding.userListProgressBar.visibility = View.GONE
        adapter.submitList(users)
    }
}
