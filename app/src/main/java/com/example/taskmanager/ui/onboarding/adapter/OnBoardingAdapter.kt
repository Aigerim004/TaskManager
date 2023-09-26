package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoarding


class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    val data = arrayListOf(
        OnBoarding(
            "https://kloop.kg/wp-content/uploads/2021/06/Frame-14-1-1024x637.png",
            "Путешествуйте. Но как-нибудь потом",
            "www.aviasales.kg"
        ),
        OnBoarding(
            "https://static.sobaka.ru/images/image/01/29/67/50/_normal.jpg?v=1591185665",
            "Я летал меня катали",
            "www.aviasales.kg"
        ),
        OnBoarding(
            "https://psjradmin.avs.io/wp-content/uploads/2022/05/sloj-1024x543.jpg",
            "Поиск дешевых авиабилетов",
            "www.aviasales.kg"
        ),

        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {

            binding.btnStart.setOnClickListener {
                onClick()
            }

            binding.btnSkip.setOnClickListener {
                onClick()

            }

            binding.btnStart.isVisible = adapterPosition == data.lastIndex
            binding.btnSkip.isVisible = adapterPosition == data.lastIndex

            binding.tvTittle.text = onBoarding.tittle
            binding.tvDesc.text = onBoarding.desc
            /*/TODO:Bind image*/
            Glide.with(binding.ivBoard).load(onBoarding.image).into(binding.ivBoard)
        }
    }
}