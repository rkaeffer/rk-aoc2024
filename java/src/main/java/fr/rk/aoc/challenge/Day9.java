package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;

public final class Day9 {

    public static long getFileSystemChecksum(List<String> input, boolean partition) {
        FileSystem fileSystem = new FileSystem(input.get(0));
        if(!partition) {
            fileSystem.reArrangeFileSystem();
        } else {
            fileSystem.reArrangeFileSystemWithPartition();
        }
        return fileSystem.getCheckSum();
    }

    static class FileSystem {

        List<Block> blocks;

        public FileSystem(String input) {
            this.blocks = new ArrayList<>();
            boolean isBlock = true;
            int id = 0;
            int pos = 0;
            for(char c : input.toCharArray()) {
                int length = Integer.parseInt(c + "");
                if(isBlock) {
                    this.blocks.add(new Block(id, pos, length));
                    id++;
                } else {
                    this.blocks.add(new Block(-1, pos, length));
                }
                pos+=length;
                isBlock = !isBlock;
            }
        }

        public void reArrangeFileSystem() {
           int curFreeBlockIndex = getNextFreeBlock(0);
           int curBlockIndex = getNextBlock(this.blocks.size()-1);
           while(curFreeBlockIndex < curBlockIndex) {
               Block curBlock = this.blocks.get(curBlockIndex);
               Block curFreeBlock = this.blocks.get(curFreeBlockIndex);
               if(curBlock.length == curFreeBlock.length) {
                   this.blocks.remove(curBlockIndex);
                   this.blocks.remove(curFreeBlockIndex);
                   curBlock.startPos = curFreeBlock.startPos;
                   this.blocks.add(curFreeBlockIndex, curBlock);
                   curFreeBlockIndex = getNextFreeBlock(curFreeBlockIndex+1);
                   curBlockIndex = getNextBlock(curBlockIndex-1);
               } else if (curBlock.length < curFreeBlock.length) {
                   this.blocks.remove(curBlockIndex);
                   curBlock.startPos = curFreeBlock.startPos;
                   this.blocks.add(curFreeBlockIndex,curBlock);
                   curFreeBlock.startPos += curBlock.length;
                   curFreeBlock.length -= curBlock.length;
                   curFreeBlockIndex++;
                   curBlockIndex = getNextBlock(curBlockIndex);
               } else {
                   this.blocks.remove(curFreeBlockIndex);
                   this.blocks.add(curFreeBlockIndex, new Block(curBlock.id, curFreeBlock.startPos, curFreeBlock.length));
                   curBlock.length -= curFreeBlock.length;
                   curFreeBlockIndex = getNextFreeBlock(curFreeBlockIndex+1);
               }
           }
        }

        public void reArrangeFileSystemWithPartition() {
            for(int curBlockIndex=this.blocks.size() - 1; curBlockIndex>0; curBlockIndex--) {
                if(this.blocks.get(curBlockIndex).id != -1) {
                    for(int curFreeBlockIndex=0; curFreeBlockIndex<curBlockIndex; curFreeBlockIndex++) {
                        if(this.blocks.get(curFreeBlockIndex).id == -1) {
                            Block curBlock = this.blocks.get(curBlockIndex);
                            Block curFreeBlock = this.blocks.get(curFreeBlockIndex);
                            if(curFreeBlock.length == curBlock.length) {
                                this.blocks.remove(curBlockIndex);
                                this.blocks.remove(curFreeBlockIndex);
                                curBlock.startPos = curFreeBlock.startPos;
                                this.blocks.add(curFreeBlockIndex, curBlock);
                                this.blocks.add(curBlockIndex, curFreeBlock);
                                break;
                            } else if (curBlock.length < curFreeBlock.length) {
                                this.blocks.remove(curBlockIndex);
                                curBlock.startPos = curFreeBlock.startPos;
                                this.blocks.add(curFreeBlockIndex, curBlock);
                                curFreeBlock.startPos += curBlock.length;
                                curFreeBlock.length -= curBlock.length;
                                break;
                            }
                        }
                    }
                }
            }
        }

        public long getCheckSum() {
            long sum = 0L;
            for(Block b : this.blocks) {
                if(b.id != -1) {
                    for(int i=b.startPos; i<b.startPos + b.length; i++) {
                        sum += (long) b.id * i;
                    }
                }
            }
            return sum;
        }

        private int getNextBlock(int current) {
            for (int i = current; i > 0; i--) {
                if(this.blocks.get(i).id != -1) {
                    return i;
                }
            }
            return Integer.MIN_VALUE;
        }

        private int getNextFreeBlock(int current) {
            for (int i = current; i < this.blocks.size(); i++) {
                if(this.blocks.get(i).id == -1) {
                    return i;
                }
            }
            return Integer.MAX_VALUE;
        }
    }

    static class Block {
        int id;

        int startPos;

        int length;

        public Block(int id, int startPos, int length) {
            this.id = id;
            this.startPos = startPos;
            this.length = length;
        }
    }
}
