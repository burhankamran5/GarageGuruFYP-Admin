package com.bkcoding.garagegurufyp_admin.repository

import android.util.Log
import com.bkcoding.garagegurufyp_admin.dto.Garage
import com.bkcoding.garagegurufyp_admin.ui.home.model.Customer
import com.bkcoding.garagegurufyp_admin.util.FirebaseRef
import com.bkcoding.garagegurufyp_admin.util.Result
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class AdminRepositoryImpl @Inject constructor(private val databaseReference: DatabaseReference): AdminRepository {
    override fun getGarages() = callbackFlow {
        val garageList = mutableListOf<Garage>()
        databaseReference.child(FirebaseRef.GARAGES).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()){
                for (ds in dataSnapshot.children) {
                    val garage: Garage? = ds.getValue(Garage::class.java)
                    if (garage != null) {
                        garageList.add(garage)
                    }
                }
                trySend(Result.Success(garageList))
            } else{
                trySend(Result.Failure(Exception("No Customer found with these details")))
            }
        }.addOnFailureListener {
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    override fun getCustomer() = callbackFlow {
        val customerList = mutableListOf<Customer>()
        databaseReference.child(FirebaseRef.CUSTOMERS).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()){
                for (ds in dataSnapshot.children) {
                    val customer: Customer? = ds.getValue(Customer::class.java)
                    if (customer != null) {
                        customerList.add(customer)
                    }
                }
                trySend(Result.Success(customerList))
            } else{
                trySend(Result.Failure(Exception("No Customer found with these details")))
            }
        }.addOnFailureListener {
            trySend(Result.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    override fun updateGarageStatus(garageId: String, status: String) = callbackFlow{
        trySend(Result.Loading)
        databaseReference.child(FirebaseRef.GARAGES).child(garageId).child("approvalStatus").setValue(status)
            .addOnSuccessListener {
                trySend(Result.Success("Data inserted Successfully.."))
            }.addOnFailureListener{
                trySend(Result.Failure(it))
            }
        awaitClose {
            close()
        }
    }
}