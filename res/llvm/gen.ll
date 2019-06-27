@.str.0 = constant [16 x i8] c"%c%c%c%c%c%c%f\0A\00"
@.str.1 = constant [4 x i8] c"%f\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca [3 x i8]
	%1 = alloca i8
	store i8 0, i8* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = alloca float
	store float 0.0, float* %3
	store i8 120, i8* %1
	%4 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 0
	store i8 97, i8* %4
	%5 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 1
	store i8 98, i8* %5
	%6 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 2
	store i8 99, i8* %6
	%7 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 0
	%8 = load i8, i8* %7
	store i8 %8, i8* %1
	%9 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 2
	%10 = load i8, i8* %1
	store i8 %10, i8* %9
	%11 = load i8, i8* %1
	%12 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 0
	%13 = load i8, i8* %12
	%14 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 1
	%15 = load i8, i8* %14
	%16 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 2
	%17 = load i8, i8* %16
	%18 = fpext float 1.0 to double
	%19 = getelementptr inbounds [16 x i8], [16 x i8]* @.str.0, i64 0, i64 0
	%20 = call i32 (i8*, ...) @printf(i8* %19, i8 %11, i8 32, i8 %13, i8 %15, i8 %17, i8 32, double %18)
	%21 = load i32, i32* %2
	%22 = call i32 @fact(i32 %21)
	ret i32 0
}

define i32 @fact(i32) {
	begin:
	%1 = alloca i32
	store i32 %0, i32* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = load i32, i32* %1
	%4 = sitofp i32 %3 to float
	%5 = fadd float %4, 1.0
	%6 = fpext float %5 to double
	%7 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i64 0, i64 0
	%8 = call i32 (i8*, ...) @printf(i8* %7, double %6)
	ret i32 0
}

