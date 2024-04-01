package com.example.utspam

import android.graphics.pdf.PdfDocument.Page

data class ApiResponse(
    val data:List<User>,
    val page: Int,
    val per_page:Int,
    val total: Int,
    val total_page:Int
)