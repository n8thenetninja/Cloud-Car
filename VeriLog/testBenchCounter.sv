// Code your testbench here
// or browse Examples
module test;
  reg clk,reset;
  wire [0:7] q;

  ripple_carry_counter rcc (q,clk,reset);

  initial begin
    $dumpfile("dump.vcd");
    $dumpvars(1,test);

    clk = 1'b0;
    reset = 1'b1;
    #10 reset = 1'b0;

    #10000;
    reset = 1'b1;
    #10 reset = 1'b0;

    #50;
    $finish;

  end

  always #5 clk = ~clk;

endmodule
