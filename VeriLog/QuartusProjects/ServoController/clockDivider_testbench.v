`timescale 1 ns/ 1 ps
module clockDivider_testbench();

// test vector input registers
reg clk;
reg reset;
// wires                                               
wire newClk;

clockDivider clkDiv (
	.clk(clk),
	.reset(reset),
	.newClk(newClk)
	);

initial                                                
begin                                                  
// code that executes only once                        
// insert code here --> begin    
$display("Running testbench");                      
reset = 0;
clk = 0;
#10
reset = 1;
#20000;

// --> end                                             
                       
end                                                    
always #10 clk = ~clk;                                              
// optional sensitivity list                           
// @(event1 or event2 or .... eventn)                                                                    
endmodule

