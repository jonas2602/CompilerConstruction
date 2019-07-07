@.str.0 = constant [4 x i8] c"%f\0A\00"

declare float @llvm.sqrt.f32(float)
declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca float
	store float 0.0, float* %0
	%1 = alloca float
	store float 0.0, float* %1
	%2 = alloca i32
	store i32 0, i32* %2
	store float 2.0, float* %1
	%3 = load float, float* %1
	%4 = sitofp i32 1 to float
	%5 = fadd float %3, %4
	%6 = sitofp i32 3 to float
	%7 = fmul float %5, %6
	%8 = call float @llvm.sqrt.f32(float %7)
	store float %8, float* %0
	%9 = load float, float* %0
	%10 = fpext float %9 to double
	%11 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%12 = call i32 (i8*, ...) @printf(i8* %11, double %10)
	store i32 9, i32* %2
	%13 = load i32, i32* %2
	%14 = sitofp i32 %13 to float
	%15 = call float @llvm.sqrt.f32(float %14)
	store float %15, float* %0
	%16 = load float, float* %0
	%17 = fpext float %16 to double
	%18 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%19 = call i32 (i8*, ...) @printf(i8* %18, double %17)
	ret i32 0
}

