@.str.0 = constant [4 x i8] c"%d\0A\00"
@.str.1 = constant [4 x i8] c"%f\0A\00"

declare float @llvm.fabs.f32(float)
declare i32 @printf(i8*, ...)
declare float @llvm.round.f32(float)
declare float @llvm.trunc.f32(float)

define i32 @main() {
	begin:
	%0 = alloca float
	store float 0.0, float* %0
	%1 = alloca float
	store float 0.0, float* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = alloca i32
	store i32 0, i32* %3
	%4 = mul i32 1, -1
	store i32 %4, i32* %2
	%5 = load i32, i32* %2
	%6 = sitofp i32 %5 to float
	%7 = call float @llvm.fabs.f32(float %6)
	%8 = fptosi float %7 to i32
	store i32 %8, i32* %2
	%9 = fmul float 3.0, -1.0
	store float %9, float* %0
	%10 = load float, float* %0
	%11 = call float @llvm.fabs.f32(float %10)
	store float %11, float* %0
	%12 = load i32, i32* %2
	%13 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%14 = call i32 (i8*, ...) @printf(i8* %13, i32 %12)
	%15 = load float, float* %0
	%16 = fpext float %15 to double
	%17 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%18 = call i32 (i8*, ...) @printf(i8* %17, double %16)
	%19 = load float, float* %0
	%20 = call float @llvm.round.f32(float %19)
	%21 = fptosi float %20 to i32
	store i32 %21, i32* %2
	%22 = load float, float* %0
	%23 = call float @llvm.trunc.f32(float %22)
	%24 = fptosi float %23 to i32
	store i32 %24, i32* %3
	%25 = load i32, i32* %2
	%26 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%27 = call i32 (i8*, ...) @printf(i8* %26, i32 %25)
	%28 = load i32, i32* %3
	%29 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%30 = call i32 (i8*, ...) @printf(i8* %29, i32 %28)
	ret i32 0
}

