#!/usr/bin/env ruby




unless ARGV.length == 1
  puts "Dude, not the right number of arguments."
  puts "Usage: ruby sort.rb InputFile > OutputFile\n"
  exit
end


input_file = ARGV[0]

#arr = Array.new
arr = Hash.new

f = File.open(input_file, "r")
f.each_line { |line|
   arr[line] = line
}

arr.sort {|a,b| a[0].downcase <=> b[0].downcase }.each { |elem|

  puts elem[0].downcase
}
  

