	.text
	.file	"test.c"
	.globl	main                    # -- Begin function main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	pushq	%rax
	.cfi_def_cfa_offset 16
	movl	$.L.str, %edi
	movl	$3, %esi
	movl	$2, %edx
	movl	$.L.str.1, %ecx
	xorl	%eax, %eax
	callq	printf
	movl	$10, %eax
	popq	%rcx
	.cfi_def_cfa_offset 8
	retq
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%i plus %i ist gleich %s.\n"
	.size	.L.str, 27

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"F\303\274nf"
	.size	.L.str.1, 6


	.ident	"clang version 7.0.0-3~ubuntu0.18.04.1 (tags/RELEASE_700/final)"
	.section	".note.GNU-stack","",@progbits
