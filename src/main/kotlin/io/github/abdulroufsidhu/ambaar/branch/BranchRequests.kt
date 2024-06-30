package io.github.abdulroufsidhu.ambaar.branch

import io.github.abdulroufsidhu.ambaar.core.Responser
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/branches")
@CrossOrigin
class BranchRequests(
    private val branchLogic: BranchLogic
) {
    @GetMapping("")
    fun getBranches(
        branch: Branch?,
        @RequestParam("business_id") businessId: String?
    ) = Responser.success {
        if (businessId == null) {
            if (branch == null) throw IllegalArgumentException("business_id and branch both are null")
            listOf( branchLogic.get( branch ) )
        } else {
            branchLogic.get(businessId)
        }
    }

    @PatchMapping("")
    fun updateBranch(
        @RequestBody branch: Branch
    ) = Responser.success {
        branchLogic.update(branch)
    }

    @DeleteMapping("/{id}")
    fun deleteBranch(@PathVariable("id") id: String) = branchLogic.delete(id)

}