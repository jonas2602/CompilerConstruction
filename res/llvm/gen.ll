declare i32* @malloc(i64)
declare i8* @realloc(i8*, i64)

define i32 @main() {
	begin:
	%0 = alloca float*
	%1 = mul i32 4, 4
	%2 = zext i32 %1 to i64
	%3 = call i32* @malloc(i64 %2)
	%4 = load float*, float** %0
	%5 = bitcast i32* %3 to float*
	store float* %5, float** %0
	%6 = load float*, float** %0
	%7 = bitcast float* %6 to i8*
	%8 = zext i32 10 to i64
	%9 = call i8* @realloc(i8* %7, i64 %8)
	%10 = bitcast i8* %9 to float*
	store float* %10, float** %0
	ret i32 0
}

