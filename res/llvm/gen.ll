@.str.0 = constant [12 x i8] c"%d%c%d%c%d\0A\00"
@.str.1 = constant [4 x i8] c"%c\0A\00"
@.str.2 = constant [4 x i8] c"%d\0A\00"

declare void @llvm.memset.p0i8.i64(i8*, i8, i64, i1)
declare void @llvm.memcpy.p0i8.p0i8.i64(i8*, i8*, i64, i1)
declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca [256 x i8]
	%1 = bitcast [256 x i8]* %0 to i8*
	call void @llvm.memset.p0i8.i64(i8* %1, i8 0, i64 256, i1 false)
	%2 = alloca [256 x i8]
	%3 = bitcast [256 x i8]* %2 to i8*
	call void @llvm.memset.p0i8.i64(i8* %3, i8 0, i64 256, i1 false)
	call void @append([256 x i8]* %2, i32 1)
	call void @append([256 x i8]* %2, i32 2)
	call void @append([256 x i8]* %2, i32 3)
	call void @append([256 x i8]* %2, i32 4)
	call void @append([256 x i8]* %2, i32 5)
	%4 = bitcast [256 x i8]* %2 to i8*
	%5 = bitcast [256 x i8]* %0 to i8*
	call void @llvm.memcpy.p0i8.p0i8.i64(i8* %5, i8* %4, i64 256, i1 false)
	%6 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 0
	%7 = load i8, i8* %6
	%8 = zext i8 %7 to i32
	%9 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 1
	%10 = load i8, i8* %9
	%11 = zext i8 %10 to i32
	%12 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 2
	%13 = load i8, i8* %12
	%14 = zext i8 %13 to i32
	%15 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%16 = call i32 (i8*, ...) @printf(i8* %15, i32 %8, i8 32, i32 %11, i8 32, i32 %14)
	%17 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 3
	%18 = load i8, i8* %17
	%19 = zext i8 %18 to i32
	%20 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 4
	%21 = load i8, i8* %20
	%22 = zext i8 %21 to i32
	%23 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 5
	%24 = load i8, i8* %23
	%25 = zext i8 %24 to i32
	%26 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%27 = call i32 (i8*, ...) @printf(i8* %26, i32 %19, i8 32, i32 %22, i8 32, i32 %25)
	%28 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 6
	%29 = load i8, i8* %28
	%30 = zext i8 %29 to i32
	%31 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 7
	%32 = load i8, i8* %31
	%33 = zext i8 %32 to i32
	%34 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 8
	%35 = load i8, i8* %34
	%36 = zext i8 %35 to i32
	%37 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%38 = call i32 (i8*, ...) @printf(i8* %37, i32 %30, i8 32, i32 %33, i8 32, i32 %36)
	%39 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%40 = call i32 (i8*, ...) @printf(i8* %39, i8 32)
	%41 = alloca [256 x i8]
	%42 = bitcast [256 x i8]* %41 to i8*
	call void @llvm.memset.p0i8.i64(i8* %42, i8 0, i64 256, i1 false)
	call void @append([256 x i8]* %41, i32 0)
	call void @appendRange([256 x i8]* %41, i32 3, i32 6)
	%43 = alloca [256 x i8]
	%44 = bitcast [256 x i8]* %43 to i8*
	call void @llvm.memset.p0i8.i64(i8* %44, i8 0, i64 256, i1 false)
	call void @subsetcore([256 x i8]* %43, [256 x i8]* %0, [256 x i8]* %41)
	%45 = bitcast [256 x i8]* %43 to i8*
	%46 = bitcast [256 x i8]* %0 to i8*
	call void @llvm.memcpy.p0i8.p0i8.i64(i8* %46, i8* %45, i64 256, i1 false)
	%47 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 0
	%48 = load i8, i8* %47
	%49 = zext i8 %48 to i32
	%50 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 1
	%51 = load i8, i8* %50
	%52 = zext i8 %51 to i32
	%53 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 2
	%54 = load i8, i8* %53
	%55 = zext i8 %54 to i32
	%56 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%57 = call i32 (i8*, ...) @printf(i8* %56, i32 %49, i8 32, i32 %52, i8 32, i32 %55)
	%58 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 3
	%59 = load i8, i8* %58
	%60 = zext i8 %59 to i32
	%61 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 4
	%62 = load i8, i8* %61
	%63 = zext i8 %62 to i32
	%64 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 5
	%65 = load i8, i8* %64
	%66 = zext i8 %65 to i32
	%67 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%68 = call i32 (i8*, ...) @printf(i8* %67, i32 %60, i8 32, i32 %63, i8 32, i32 %66)
	%69 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 6
	%70 = load i8, i8* %69
	%71 = zext i8 %70 to i32
	%72 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 7
	%73 = load i8, i8* %72
	%74 = zext i8 %73 to i32
	%75 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 8
	%76 = load i8, i8* %75
	%77 = zext i8 %76 to i32
	%78 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%79 = call i32 (i8*, ...) @printf(i8* %78, i32 %71, i8 32, i32 %74, i8 32, i32 %77)
	%80 = load [256 x i8], [256 x i8]* %0
	%81 = call i1 @operatorIN(i32 1, [256 x i8] %80)
	%82 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.2, i32 0, i32 0
	%83 = call i32 (i8*, ...) @printf(i8* %82, i1 %81)
	%84 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%85 = call i32 (i8*, ...) @printf(i8* %84, i8 32)
	%86 = alloca [256 x i8]
	%87 = bitcast [256 x i8]* %86 to i8*
	call void @llvm.memset.p0i8.i64(i8* %87, i8 0, i64 256, i1 false)
	call void @append([256 x i8]* %86, i32 5)
	call void @appendRange([256 x i8]* %86, i32 6, i32 10)
	%88 = alloca [256 x i8]
	%89 = bitcast [256 x i8]* %88 to i8*
	call void @llvm.memset.p0i8.i64(i8* %89, i8 0, i64 256, i1 false)
	call void @addsetcore([256 x i8]* %88, [256 x i8]* %0, [256 x i8]* %86)
	%90 = bitcast [256 x i8]* %88 to i8*
	%91 = bitcast [256 x i8]* %0 to i8*
	call void @llvm.memcpy.p0i8.p0i8.i64(i8* %91, i8* %90, i64 256, i1 false)
	%92 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 0
	%93 = load i8, i8* %92
	%94 = zext i8 %93 to i32
	%95 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 1
	%96 = load i8, i8* %95
	%97 = zext i8 %96 to i32
	%98 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 2
	%99 = load i8, i8* %98
	%100 = zext i8 %99 to i32
	%101 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%102 = call i32 (i8*, ...) @printf(i8* %101, i32 %94, i8 32, i32 %97, i8 32, i32 %100)
	%103 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 3
	%104 = load i8, i8* %103
	%105 = zext i8 %104 to i32
	%106 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 4
	%107 = load i8, i8* %106
	%108 = zext i8 %107 to i32
	%109 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 5
	%110 = load i8, i8* %109
	%111 = zext i8 %110 to i32
	%112 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%113 = call i32 (i8*, ...) @printf(i8* %112, i32 %105, i8 32, i32 %108, i8 32, i32 %111)
	%114 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 6
	%115 = load i8, i8* %114
	%116 = zext i8 %115 to i32
	%117 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 7
	%118 = load i8, i8* %117
	%119 = zext i8 %118 to i32
	%120 = getelementptr inbounds [256 x i8], [256 x i8]* %0, i32 0, i32 8
	%121 = load i8, i8* %120
	%122 = zext i8 %121 to i32
	%123 = getelementptr inbounds [12 x i8], [12 x i8]* @.str.0, i32 0, i32 0
	%124 = call i32 (i8*, ...) @printf(i8* %123, i32 %116, i8 32, i32 %119, i8 32, i32 %122)
	%125 = load [256 x i8], [256 x i8]* %0
	%126 = call i1 @operatorIN(i32 8, [256 x i8] %125)
	%127 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.2, i32 0, i32 0
	%128 = call i32 (i8*, ...) @printf(i8* %127, i1 %126)
	%129 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%130 = call i32 (i8*, ...) @printf(i8* %129, i8 32)
	ret i32 0
}

define void @append([256 x i8]*, i32) {
	begin:
	%2 = alloca [256 x i8]*
	store [256 x i8]* %0, [256 x i8]** %2
	%3 = load [256 x i8]*, [256 x i8]** %2
	%4 = alloca i32
	store i32 %1, i32* %4
	%5 = load i32, i32* %4
	%6 = call i1 @inrangei(i32 0, i32 256, i32 %5)
	br i1 %6, label %7, label %10
	
	; <label>:7:
	%8 = load i32, i32* %4
	%9 = getelementptr inbounds [256 x i8], [256 x i8]* %3, i32 0, i32 %8
	store i8 1, i8* %9
	br label %10
	
	; <label>:10:
	ret void 
}

define i1 @inrangei(i32, i32, i32) {
	begin:
	%3 = alloca i32
	store i32 %0, i32* %3
	%4 = alloca i32
	store i32 %1, i32* %4
	%5 = alloca i32
	store i32 %2, i32* %5
	%6 = alloca i1
	store i1 0, i1* %6
	%7 = load i32, i32* %5
	%8 = load i32, i32* %3
	%9 = sub i32 %7, %8
	%10 = load i32, i32* %4
	%11 = load i32, i32* %3
	%12 = sub i32 %10, %11
	%13 = icmp sle i32 %9, %12
	store i1 %13, i1* %6
	%14 = load i1, i1* %6
	ret i1 %14
}

define void @appendRange([256 x i8]*, i32, i32) {
	begin:
	%3 = alloca [256 x i8]*
	store [256 x i8]* %0, [256 x i8]** %3
	%4 = load [256 x i8]*, [256 x i8]** %3
	%5 = alloca i32
	store i32 %1, i32* %5
	%6 = alloca i32
	store i32 %2, i32* %6
	%7 = alloca i32
	store i32 0, i32* %7
	%8 = load i32, i32* %5
	store i32 %8, i32* %7
	br label %9
	
	; <label>:9:
	%10 = load i32, i32* %7
	%11 = load i32, i32* %6
	%12 = icmp sle i32 %10, %11
	br i1 %12, label %13, label %18
	
	; <label>:13:
	%14 = load i32, i32* %7
	call void @append([256 x i8]* %4, i32 %14)
	br label %15
	
	; <label>:15:
	%16 = load i32, i32* %7
	%17 = add i32 %16, 1
	store i32 %17, i32* %7
	br label %9
	
	; <label>:18:
	ret void 
}

define void @subsetcore([256 x i8]*, [256 x i8]*, [256 x i8]*) {
	begin:
	%3 = alloca [256 x i8]*
	store [256 x i8]* %0, [256 x i8]** %3
	%4 = load [256 x i8]*, [256 x i8]** %3
	%5 = alloca [256 x i8]*
	store [256 x i8]* %1, [256 x i8]** %5
	%6 = load [256 x i8]*, [256 x i8]** %5
	%7 = alloca [256 x i8]*
	store [256 x i8]* %2, [256 x i8]** %7
	%8 = load [256 x i8]*, [256 x i8]** %7
	%9 = alloca i32
	store i32 0, i32* %9
	store i32 0, i32* %9
	br label %10
	
	; <label>:10:
	%11 = load i32, i32* %9
	%12 = icmp sle i32 %11, 255
	br i1 %12, label %13, label %27
	
	; <label>:13:
	%14 = load i32, i32* %9
	%15 = getelementptr inbounds [256 x i8], [256 x i8]* %4, i32 0, i32 %14
	%16 = load i32, i32* %9
	%17 = getelementptr inbounds [256 x i8], [256 x i8]* %6, i32 0, i32 %16
	%18 = load i32, i32* %9
	%19 = getelementptr inbounds [256 x i8], [256 x i8]* %8, i32 0, i32 %18
	%20 = load i8, i8* %19
	%21 = xor i8 %20, -1
	%22 = load i8, i8* %17
	%23 = and i8 %22, %21
	store i8 %23, i8* %15
	br label %24
	
	; <label>:24:
	%25 = load i32, i32* %9
	%26 = add i32 %25, 1
	store i32 %26, i32* %9
	br label %10
	
	; <label>:27:
	ret void 
}

define i1 @operatorIN(i32, [256 x i8]) {
	begin:
	%2 = alloca i32
	store i32 %0, i32* %2
	%3 = alloca [256 x i8]
	store [256 x i8] %1, [256 x i8]* %3
	%4 = alloca i1
	store i1 0, i1* %4
	%5 = load i32, i32* %2
	%6 = call i1 @inrangei(i32 0, i32 256, i32 %5)
	br i1 %6, label %7, label %12
	
	; <label>:7:
	%8 = load i32, i32* %2
	%9 = getelementptr inbounds [256 x i8], [256 x i8]* %3, i32 0, i32 %8
	%10 = load i8, i8* %9
	%11 = trunc i8 %10 to i1
	store i1 %11, i1* %4
	br label %13
	
	; <label>:12:
	store i1 0, i1* %4
	br label %13
	
	; <label>:13:
	%14 = load i1, i1* %4
	ret i1 %14
}

define void @addsetcore([256 x i8]*, [256 x i8]*, [256 x i8]*) {
	begin:
	%3 = alloca [256 x i8]*
	store [256 x i8]* %0, [256 x i8]** %3
	%4 = load [256 x i8]*, [256 x i8]** %3
	%5 = alloca [256 x i8]*
	store [256 x i8]* %1, [256 x i8]** %5
	%6 = load [256 x i8]*, [256 x i8]** %5
	%7 = alloca [256 x i8]*
	store [256 x i8]* %2, [256 x i8]** %7
	%8 = load [256 x i8]*, [256 x i8]** %7
	%9 = alloca i32
	store i32 0, i32* %9
	store i32 0, i32* %9
	br label %10
	
	; <label>:10:
	%11 = load i32, i32* %9
	%12 = icmp sle i32 %11, 255
	br i1 %12, label %13, label %26
	
	; <label>:13:
	%14 = load i32, i32* %9
	%15 = getelementptr inbounds [256 x i8], [256 x i8]* %4, i32 0, i32 %14
	%16 = load i32, i32* %9
	%17 = getelementptr inbounds [256 x i8], [256 x i8]* %6, i32 0, i32 %16
	%18 = load i32, i32* %9
	%19 = getelementptr inbounds [256 x i8], [256 x i8]* %8, i32 0, i32 %18
	%20 = load i8, i8* %17
	%21 = load i8, i8* %19
	%22 = or i8 %20, %21
	store i8 %22, i8* %15
	br label %23
	
	; <label>:23:
	%24 = load i32, i32* %9
	%25 = add i32 %24, 1
	store i32 %25, i32* %9
	br label %10
	
	; <label>:26:
	ret void 
}

