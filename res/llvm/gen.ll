

define i32 @main() {
	begin:
	br i1 1, label %0, label %4
	
	; <label>:0:
	br i1 1, label %1, label %2
	
	; <label>:1:
	br label %3
	
	; <label>:2:
	br label %3
	
	; <label>:3:
	br label %5
	
	; <label>:4:
	br label %5
	
	; <label>:5:
	ret i32 0
}

