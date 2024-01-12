package com.example.myapplicationfood.activity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplicationfood.Domain.Foods
import com.example.myapplicationfood.databinding.ActivityDetailBinding


class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var objecta: Foods
    private var num = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = resources.getColor(R.color.black)

        getIntentExtra()
        setVariable()
    }

    private fun setVariable() {
        managmentCart = ManagmentCart(this)

        binding.backBtn.setOnClickListener { finish() }

        Glide.with(this)
            .load(objecta.imagePath)
            .into(binding.pic)

        binding.priceTxt.text = "$" + objecta.price
        binding.titleTxt.text = objecta.title
        binding.descriptionTxt.text = objecta.description
        binding.rateTxt.text = objecta.star.toString() + " Rating"
        binding.ratingBar.rating = objecta.star.toFloat()
        binding.totalTxt.text = (num * objecta.price).toString() + "$"

        binding.plusBtn.setOnClickListener {
            num++
            binding.numTxt.text = num.toString() + " "
            binding.totalTxt.text = "$" + (num * objecta.price)
        }

        binding.minusBtn.setOnClickListener {
            if (num > 1) {
                num--
                binding.numTxt.text = num.toString()
                binding.totalTxt.text = "$" + (num * objecta.price)
            }
        }

        binding.addBtn.setOnClickListener {
            objecta.numberInCart = num
            managmentCart.insertFood(object)
        }
    }

    private fun getIntentExtra() {
        objecta = intent.getSerializableExtra("object") as Foods
    }
}
