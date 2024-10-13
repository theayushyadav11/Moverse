module MyModule::Donara {

    use aptos_framework::coin::{Self, Coin};
    use aptos_framework::aptos_account;
    use aptos_framework::signer;

    struct AptosCoin has store, key { }

    public fun donate(sender: &signer, recipient: address, amount: u64) {
        let sender_address = signer::address_of(sender);
        let coin_balance = coin::balance<AptosCoin>(sender_address);
        assert!(coin_balance >= amount, 0x1);
        coin::transfer<AptosCoin>(sender, recipient, amount);
        aptos_account::emit_event(sender, sender_address, recipient, amount);
    }

    public fun init(sender: &signer, initial_mint: u64) {
        let aptos_coin = coin::mint<AptosCoin>(initial_mint, signer::address_of(sender));
        aptos_framework::aptos_account::deposit(sender, aptos_coin);
    }

}