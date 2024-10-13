package com.theayushyadav11.moverse.starting


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.theayushyadav11.moverse.R


class ViewPagerAdapter(private val items: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val lottieAnimationView: LottieAnimationView =
            itemView.findViewById(R.id.lottieAnimationView)
        val dots = listOf<CircularRevealCardView>(
        itemView.findViewById(R.id.dot1),
            itemView.findViewById(R.id.dot2),
            itemView.findViewById(R.id.dot3)
        )
        val matter: TextView = itemView.findViewById(R.id.matter)
        val matterDis: TextView = itemView.findViewById(R.id.matterDis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.caraousal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // holder.imageView.setImageResource(items[position])

        // URL of the Lottie animation
        val matters= listOf(
            "Compassion in Action: Every Donation Matters.",
            "Connecting the Servers and the Receivers.",
            "Helping the Needy, One Donation at a Time."
        )
        val matterDiscription = listOf(
            "Welcome to Donara! A web3 crowdfunding platform dedicated to empowering communities by transforming your digital donations into hope for war victims.\n",
            "Every donation you make on Donara is a step towards a better future for those in need. We believe that every donation matters, no matter how small. Your donation can help us make a difference in the lives of those who need it the most.\n",
            "Join us in our mission to make the world a better place, one donation at a time. Together, we can create a brighter future for everyone.",
        )
        val animationUrl = items[position]
        // Load animation from URL
        holder.lottieAnimationView.setAnimationFromUrl(animationUrl)
        holder.lottieAnimationView.playAnimation()
        for(dot in holder.dots){
            if(holder.dots.indexOf(dot) == position) {
                dot.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.bg_screen1))
            }
                else
                dot.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.white))
        }
        holder.matter.text = matters[position]
        holder.matterDis.text = matterDiscription[position]


    }

    override fun getItemCount(): Int = items.size
}
