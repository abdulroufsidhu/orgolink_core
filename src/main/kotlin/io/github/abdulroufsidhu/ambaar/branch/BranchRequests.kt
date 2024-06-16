package io.github.abdulroufsidhu.ambaar.branch

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
class BranchRequests(
    private val branchLogic: BranchLogic
) {
    @GetMapping("")
    fun getBranches(
        @RequestParam("branch") branch: Branch?,
        @RequestParam("business_id") businessId: String?
    ): List<Branch>? {
        if (businessId == null) return listOf(
            branchLogic.get(
                branch ?: throw IllegalArgumentException("business_id and branch both are null")
            )
        )
        return branchLogic.get(businessId)
    }

    @PutMapping("")
    fun createBranch(
        @RequestBody branch: Branch
    ) = branchLogic.create(branch)

    @PatchMapping("")
    fun updateBranch(
        @RequestBody branch: Branch
    ) = branchLogic.update(branch)

    @DeleteMapping("/{id}")
    fun deleteBranch(@PathVariable("id") id: String) = branchLogic.delete(id)

}