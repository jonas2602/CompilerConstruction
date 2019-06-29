

define i32 @main() {
	begin:
	%0 = alloca i1
	store i1 0, i1* %0
	%1 = alloca i32
	store i32 0, i32* %1
	%2 = alloca float
	store float 0.0, float* %2
	%3 = alloca i32
	store i32 0, i32* %3
	%4 = mul i32 5, -1
	%5 = add i32 2, %4
	store i32 %5, i32* %1
	%6 = load i32, i32* %1
	%7 = mul i32 %6, -1
	%8 = fmul float 1.0, -1.0
	%9 = sitofp i32 %7 to float
	%10 = fadd float %9, %8
	store float %10, float* %2
	%11 = xor i1 1, 1
	store i1 %11, i1* %0
	%12 = load i32, i32* %1
	%13 = load float, float* %2
	%14 = sitofp i32 %12 to float
	%15 = fcmp oge float %14, %13
	store i1 %15, i1* %0
	ret i32 0
}

