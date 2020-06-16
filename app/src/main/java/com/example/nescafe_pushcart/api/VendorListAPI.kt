package com.example.nescafe_pushcart.api

import com.example.nescafe_pushcart.model.listofvendors.VendorList

interface VendorListAPI {

   suspend fun showVendors():VendorList

}