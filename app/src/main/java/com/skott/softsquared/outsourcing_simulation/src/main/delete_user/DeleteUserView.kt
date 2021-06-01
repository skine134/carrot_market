package com.skott.softsquared.outsourcing_simulation.src.main.delete_user

interface DeleteUserView {
    fun onPatchDeleteUserSuccess()
    fun onPatchDeleteUserFailure(message:String)
}