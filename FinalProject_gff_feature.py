#!/usr/bin/env python3
#arahmou1@bfx3.aap.jh.edu

import textwrap
import pyfaidx
import sys
from gff3 import Gff3


# Obtaining input from user through the terminal commandline
# to follow the order used in the command line provided in the assignment
#splits the string using = sign
source_gff = sys.argv[1].split('=')[1]
type = sys.argv[2].split('=')[1]
attribute = sys.argv[3].split('=')[1]
value = sys.argv[4].split('=')[1]

#input file
file = open(source_gff)

feature = 0
index = -1


# create Fasta file 
Mywriter = False

fasta = open('output.fa', 'w+')

with open(source_gff, "r") as fasta_file:
   for line in fasta_file:
        #if line == "##FASTA\n":
            #print (str(line))
        if line == "##FASTA\n" or Mywriter:
           Mywriter = True
           fasta.write(str(line))
            
fasta.close()

#using pyfaidx to access sequences in this indexed files without having to load a huge amount of data to the memory
#it allows to easily get the start and end coordinates 
seq = pyfaidx.Fasta('output.fa')

#gff_export_feature function is to export the features from the gff annotation file

def gff_export_feature():
    
    #using Gff3 to manipulate genomic features
    gff = Gff3()
    #read through the file/parsing
    gff.parse(source_gff)  

    global feature
    global index
    
    for line in gff.lines:
        if line['type'] == type and line['attributes'].get(attribute, 0) == value:
            feature = feature + 1
            index = line['line_index']

    #error messages if no or more than one feature matches 
    if feature > 1:
        print('Attention: More than one match found.')
    elif feature == 0:
        print('Attention: No match found.')
    else:
        print('Sequence coordinates are found at start: ' + str(gff.lines[index]['start']) + ' and end: ' + str(gff.lines[index]['end']) + ' strand: ' + str(gff.lines[index]['strand']))
        m = seq[gff.lines[index]['seqid']]
        print(">" + type + ":" + attribute + ":" + value)

        # to print in FASTA format we need to print 60 characters per line
        i = 0
        while i < m.__len__():
             #print(textwrap.fill(m[i-1:m.__len__()], 60))
             print(m[i:i + 60])
             i = i + 60

#executing the function
Def main():
     gff_export_feature()

if __name__ == '__main__':
     main()
