package com.theayushyadav11.moverse.LoginAndRegistration

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.theayushyadav11.moverse.R
import com.theayushyadav11.moverse.databinding.ActivityLogOptBinding
import java.net.URL


class LogOptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogOptBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLogOptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnSignUp.setOnClickListener{
            val  ZKWALLET = "https://zkwallet.welldonestudio.io"
            val CHAIN_NAME = "aptos:testnet";
            val uri = Uri.parse(ZKWALLET).buildUpon()
                .appendPath("connect")
                .appendQueryParameter("chain", CHAIN_NAME)
                .appendQueryParameter("callback", "${URLUtil.guessUrl(windowLocation())}/wallet/zkWallet/sign-transaction")
                .appendQueryParameter(
                    "jsonrpc", """
                {
                  "jsonrpc": "2.0",
                  "id": 0,
                  "method": "dapp:accounts"
                }
                """.trimIndent()
                )
                .build()

            // Redirect to the generated URL
            val inten = android.content.Intent(android.content.Intent.ACTION_VIEW, uri)
            startActivity(inten)




            val walletLoginUrl = "https://remix.ethereum.org/?#activate=wds-code-remix"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(walletLoginUrl))
            startActivity(intent)

        }


    }
}