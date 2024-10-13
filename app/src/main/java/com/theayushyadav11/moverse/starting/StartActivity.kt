package com.theayushyadav11.moverse.starting


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.theayushyadav11.moverse.LoginAndRegistration.LogOptActivity
import com.theayushyadav11.moverse.R
import xyz.mcxross.kaptos.Aptos
import xyz.mcxross.kaptos.account.Account
import xyz.mcxross.kaptos.client.ClientConfig
import xyz.mcxross.kaptos.core.crypto.Ed25519PrivateKey
import xyz.mcxross.kaptos.model.AptosConfig
import xyz.mcxross.kaptos.model.AptosSettings
import xyz.mcxross.kaptos.model.HexInput
import xyz.mcxross.kaptos.model.Network
import xyz.mcxross.kaptos.model.TypeTagStruct

import xyz.mcxross.kaptos.model.U64
import xyz.mcxross.kaptos.model.entryFunctionData
import xyz.mcxross.kaptos.model.functionArguments
import xyz.mcxross.kaptos.model.typeArguments
import xyz.mcxross.kaptos.util.runBlocking


class StartActivity : AppCompatActivity() {
    lateinit var viewPager: ViewPager2
    lateinit var btnNext: MaterialButton
    lateinit var btnPre: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById<MaterialButton>(R.id.btnNext)
        btnPre = findViewById<MaterialButton>(R.id.btnPre)

        val images = listOf(
            "https://lottie.host/ddd4df45-2bec-424e-9034-7b6e2e8d9e65/ubvWuFaUqd.json",
            "https://lottie.host/6066844d-0b3c-49b5-a2d5-410714d99fdd/03C6NsWTzm.json",
            "https://lottie.host/2ce5384e-4324-4d7c-8a24-efac6b82220e/z44t5MwsFk.json"
        )
        initialise()


        val adapter = ViewPagerAdapter(images)
        viewPager.adapter = adapter
        btnNext.setOnClickListener {
            if (viewPager.currentItem < images.size - 1) {
                viewPager.currentItem += 1
            } else {

                startActivity(Intent(this@StartActivity, LogOptActivity::class.java))
                finish()
            }
        }

        // Navigate Backward
        btnPre.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem -= 1
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    btnNext.text = "Start"

                } else {
                    btnNext.text = "Next"
                }
                if (position == 0) {
                    btnPre.isVisible = false
                } else {
                    btnPre.isVisible = true
                }
            }
        })
    }

    private fun initialise() = runBlocking {

        val settings =
            AptosSettings(network = Network.TESTNET, clientConfig = ClientConfig(maxRetries = 10))
        val aptosConfig = AptosConfig(settings = settings)
        val aptos = Aptos(aptosConfig)
        val privateKey =
            Ed25519PrivateKey("0x55a3d3815e548d835553fd809775aebb8f5a5b35728ba417df6d675090c173fa")
        val myAccount = Account.fromPrivateKey(privateKey)
        val privateKeyR =
            Ed25519PrivateKey("0xb3966ac75e0f3f416cbc89494332153d3e32a5ae69b4248ec69fffc015ad2232")
        val reddyAccount = Account.fromPrivateKey(privateKeyR)
        val SEND_AMOUNT = 1UL



        println("Created accounts on chain")
        println("Alice's balance: ${aptos.getAccountAPTAmount(myAccount.accountAddress)}")
        println("Bob's balance: ${aptos.getAccountAPTAmount(reddyAccount.accountAddress)}")
        println("=============================================")
        println(
            "Building transaction to send ${SEND_AMOUNT / 100_000_000u} APT to Bob: ${reddyAccount.accountAddress}"
        )


        // val txn =
//            aptos.buildTransaction.simple(
//                sender = myAccount.accountAddress,
//                data =
//                entryFunctionData {
//                    function = "0x1::coin::transfer"
//                    typeArguments = typeArguments { +TypeTagStruct("0x1::aptos_coin::AptosCoin") }
//                    functionArguments = functionArguments {
//                        +reddyAccount.accountAddress
//                        +U64(SEND_AMOUNT)
//                    }
//                },
//            )

        // Sign and submit the transaction
//        val commitedTransaction = aptos.signAndSubmitTransaction(myAccount, txn)
//
//        val executedTransaction =
//            aptos.waitForTransaction(
//                HexInput.fromString(commitedTransaction.expect("Transaction failed").hash)
//            )
//
//        println(
//            "Transaction wait response: $executedTransaction\n============================================="
//        )
//
//        val myAccountNewBalance =
//            aptos.getAccountAPTAmount(myAccount.accountAddress).expect("myAccount's account does not exist")
//        val reddyAccountNewBalance =
//            aptos.getAccountAPTAmount(reddyAccount.accountAddress).expect("reddyAccount's account does not exist")
//
//        println("myAccount's new balance: $myAccountNewBalance")
//        println("reddyAccount's new balance: $reddyAccountNewBalance")


    }

}