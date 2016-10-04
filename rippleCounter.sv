// T flip flop
module tff(q,clk,reset);
	output reg q;
  	input clk, reset;

  always@(posedge reset or negedge clk) begin
    if (reset) begin
      q <= 1'b0;
    end else begin
      q = ~q;
    end
  end
endmodule


module ripple_carry_counter (q,clk,reset);
  output [7:0] q;
  input clk, reset;

  tff tff0 (q[0],clk,reset);
  tff tff1 (q[1],q[0],reset);
  tff tff2 (q[2],q[1],reset);
  tff tff3 (q[3],q[2],reset);
  tff tff4 (q[4],q[3],reset);
  tff tff5 (q[5],q[4],reset);
  tff tff6 (q[6],q[5],reset);
  tff tff7 (q[7],q[6],reset);

endmodule
