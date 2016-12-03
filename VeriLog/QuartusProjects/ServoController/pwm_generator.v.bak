/*
PWM Generator

This module will generate a PWM output signal whose duty cycle is determined
by the duty_cycle input.  Valid duty_cycle values include the full range from 0 to (2^N)-1,
where N is the PWM_DEPTH in bits (for example, 0-255 for an 8 bit depth). 

This design uses an internal counter and comparator to control the PWM output.  
The PWM output can be disabled either by applying the value of zero to the 
duty_cycle input, or by holding the module in reset.

How to create an instance using Verilog 2001 syntax:
	-	The PWM_DEPTH parameter determines how many bits are used
		for the PWM target input and the internal PWM counter.
	
	// PWM generator instance
	pwm_generator #(.PWM_DEPTH(8)) pwm0			// pwm0 is your instance name
	(//ports 
		.pwm( <your pwm signal> ),					// PWM output signal
		.duty_cycle( <your pwm duty cycle> ),	// PWM duty cycle (vector)
		.rst_n( <your reset signal> ),			// Active-low reset
		.clk( <your clock signal> )				// Clock signal
	);

Note:		Verilog 2001, ANSI C Syntax
Author:	Mark Young
Date:		2016/05/19
*/

module pwm_generator 
#(parameter PWM_DEPTH=8)							// PWM depth in bits (default)
(//ports
	output reg	pwm,									// PWM output signal
	input  wire [PWM_DEPTH-1:0] duty_cycle,	// PWM target value (vector)
	input  wire rst_n,								// Active-low reset
	input  wire clk									// Clock signal
);

//internal signals
reg [PWM_DEPTH-1:0] count;							// internal count for PWM comparator
wire pwm_next;											// result of PWM comparison


// Comparator (direct assignment; will synthesize combinational logic)
assign pwm_next = (duty_cycle) ? (count <= duty_cycle) : 1'b0;

// PWM output signal (synchronous logic)
always @ (negedge rst_n, posedge clk)
begin
	if (!rst_n)
		pwm <= 1'b0;
	else
		pwm <= pwm_next;			//the comparator result is registered synchronously in pwm
end

// Internal counter for PWM comparator (synchronous logic)
always @ (negedge rst_n, posedge clk)
begin
	if (!rst_n)
		count <= 1'b0;
	else
		count <= count + 1'b1;	//this increment will generate a combinational adder,
										//and the result will be registered synchronously in count
end

endmodule

